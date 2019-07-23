package service.impl;

import dao.*;
import dao.impl.*;
import entity.FavorEntity;
import entity.FriendshipEntity;
import entity.UsersEntity;
import entity.ViewhistoryEntity;
import service.FriendshipService;
import service.UserService;

import java.sql.Timestamp;
import java.util.*;

public class UserServiceImp implements UserService {
    private UsersDao usersDao = new UsersDaoImp();
    private FriendshipDao friendshipDao = new FriendshipDaoImp();
    private FavorDao favorDao = new FavorDaoImp();
    private FriendshipService friendshipService = new FriendshipServiceImp();
    private ViewHistoryDao viewHistoryDao = new ViewHistoryDaoImp();
    private DeleteHistoryDao deleteHistoryDao = new DeleteHistoryDaoImp();

    @Override
    public boolean queryDeleteHistory(int userId, int artworkId) {
        return deleteHistoryDao.query(userId,artworkId);
    }

    @Override
    public void updateViewHistory(int userId, int artworkId) {
        ViewhistoryEntity viewhistoryEntity = viewHistoryDao.query(userId,artworkId);
        if (viewhistoryEntity==null)viewHistoryDao.add(userId,artworkId);
        else {
            int viewTime = viewhistoryEntity.getViewTime();
            viewHistoryDao.update(userId,artworkId,viewTime+1);
        }
    }

    @Override
    public void updateDeleteHistory(int userId, int artworkId) {
        if (!deleteHistoryDao.query(userId, artworkId)) {
            deleteHistoryDao.add(userId, artworkId);
            System.out.println("add");
        } else {
            deleteHistoryDao.delete(userId, artworkId);
            System.out.println("delete");
        }
    }

    @Override
    public UsersEntity login(String username, String password) {
        UsersEntity user = usersDao.query(username, password);

        if (user != null) {// found matched user
            // refresh last login time
            user.setTime(new Timestamp(new Date().getTime()));
            update(user);

            user = usersDao.query(username, password);
        }

        return user;
    }

    @Override
    public boolean checkSignUpUsername(String username) {
        return usersDao.query(username) == null;
    }

    @Override
    public void signUp(UsersEntity user) {
        usersDao.insert(user);
    }

    @Override
    public UsersEntity get(String username) {
        return usersDao.query(username);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersDao.queryAll();
    }

    @Override
    public UsersEntity update(UsersEntity user) {
        usersDao.update(user);
        return usersDao.query(user.getName());
    }

    @Override
    public boolean checkModifyUser(int userId, String password) {
        return usersDao.query(userId, password) != null;
    }

    @Override
    public void addUser(UsersEntity user) {
        usersDao.insert(user);
    }

    @Override
    public void modifyUser(UsersEntity user) {
        usersDao.updateInAdmin(user);
    }

    @Override
    public void deleteUser(String name) {
        usersDao.delete(name);
    }

    @Override
    public void deleteUser(int userId) {
        usersDao.delete(userId);
    }

    @Override
    public List<UsersEntity> getFriends(int userId) {
        List<UsersEntity> friends = new ArrayList<>();

        // get all friendships of user
        List<FriendshipEntity> friendships = friendshipDao.getFriendships(userId);
        for (FriendshipEntity friendship : friendships) {
            int friendUserId = friendship.getSenderId() == userId ? friendship.getReceiverId() : friendship.getSenderId();
            friends.add(usersDao.query(friendUserId));
        }

        return friends;
    }

    @Override
    public List<UsersEntity> getFriendRequestSenders(int receiverId) {
        List<UsersEntity> senders = new ArrayList<>();

        // get all friendship requests
        List<FriendshipEntity> requests = friendshipDao.getFriendshipRequests(receiverId);
        for (FriendshipEntity request : requests) {
            senders.add(usersDao.query(request.getSenderId()));
        }

        return senders;
    }

    @Override
    public List<UsersEntity> getUserByNameLike(String username) {
        return usersDao.queryLike(username);
    }

    @Override
    public UsersEntity get(int userId) {
        return usersDao.query(userId);
    }

    @Override
    public Map<UsersEntity, String> getRecommendedFriends(int userId) {
        Map<Integer,Integer> allRecommendedFriends = new HashMap<>();
        List<FavorEntity> list = favorDao.getFavors(userId);
        for (FavorEntity favorEntity : list){
            int artworkId = favorEntity.getArtworkId();
            List<FavorEntity> tmpFavorList = favorDao.getFavorsByArtworkId(artworkId);
            for (FavorEntity tmpFavor: tmpFavorList){
                int recommendedUserId = tmpFavor.getUserId();
                if (allRecommendedFriends.containsKey(recommendedUserId)){
                    for (Map.Entry<Integer,Integer> tmpMap:allRecommendedFriends.entrySet()){
                        if (tmpMap.getKey()==recommendedUserId){
                            tmpMap.setValue(tmpMap.getValue()+1);
                        }
                    }
                }else {
                    allRecommendedFriends.put(recommendedUserId,1);
                }
            }
        }
        Map<UsersEntity,String> RecommendedFriends = new HashMap<>();
        allRecommendedFriends = sortByValueDescending(allRecommendedFriends);
        List<UsersEntity> friendsList = getFriends(userId);
        allRecommendedFriends.remove(userId);
        for (UsersEntity tmpUser:friendsList){
            allRecommendedFriends.remove(tmpUser.getUserId());
        }
        int flag = 0;
        for (Map.Entry<Integer,Integer> tmpMap:allRecommendedFriends.entrySet()){
            if (flag == 3){
                break;
            }
            UsersEntity usersEntity = usersDao.query(tmpMap.getKey());
            String status = friendshipService.getFriendshipStatus(userId,tmpMap.getKey());
            RecommendedFriends.put(usersEntity,status);
//            RecommendedFriends.add(usersDao.query(tmpMap.getKey()));
            flag++;
        }
        return RecommendedFriends;
    }

    //降序排序
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort((Map.Entry<K, V> o1, Map.Entry<K, V> o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
