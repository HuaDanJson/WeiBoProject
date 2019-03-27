package cool.weiboproject.android.utils;

import android.content.Context;

import java.util.List;

import cool.android.greendao.DaoManager;
import cool.android.greendao.WeiBoBeanDao;
import cool.weiboproject.android.bean.WeiBoBean;

public class WeiBoDaoUtils {

    private WeiBoBeanDao weiBoBeanDao;

    private static WeiBoDaoUtils weiBoDaoUtils = null;

    public WeiBoDaoUtils(Context context) {
        weiBoBeanDao = DaoManager.getInstance(context).getNewSession().getWeiBoBeanDao();
    }

    public static WeiBoDaoUtils getInstance() {
        return weiBoDaoUtils;
    }

    public static void Init(Context context) {
        if (weiBoDaoUtils == null) {
            weiBoDaoUtils = new WeiBoDaoUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(WeiBoBean weiBoBean) {
        weiBoBeanDao.insertOrReplace(weiBoBean);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<WeiBoBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            weiBoBeanDao.insertOrReplaceInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据操作
     *
     * @param dbUserInvestment
     * @return
     */
    public boolean deleteOneData(WeiBoBean dbUserInvestment) {
        boolean flag = false;
        try {
            weiBoBeanDao.delete(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据 ByKey操作
     *
     * @return
     */
    public boolean deleteOneDataByKey(long id) {
        boolean flag = false;
        try {
            weiBoBeanDao.deleteByKey(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中批量删除数据操作
     *
     * @return
     */
    public boolean deleteManData(List<WeiBoBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            weiBoBeanDao.deleteInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中数据全部删除
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            weiBoBeanDao.deleteAll();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 完成对数据库更新数据操作
     *
     * @return
     */
    public boolean updateData(WeiBoBean dbUserInvestment) {
        boolean flag = false;
        try {
            weiBoBeanDao.update(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库批量更新数据操作
     *
     * @return
     */
    public boolean updateManData(List<WeiBoBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            weiBoBeanDao.updateInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库查询数据操作
     *
     * @return
     */
    public WeiBoBean queryOneData(long id) {
        return weiBoBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<WeiBoBean> queryAllData() {
        return weiBoBeanDao.loadAll();
    }

    /**
     * 完成对数据库条件查询数据操作 senderId
     *
     * @return
     */
    public List<WeiBoBean> queryCurrentCollectionData(String currentUserName) {
        return weiBoBeanDao.queryBuilder().where(WeiBoBeanDao.Properties.CollectionUserName.eq(currentUserName)).build().list();
    }

//    /**
//     * 完成对数据库条件查询数据操作 senderId
//     *
//     * @return
//     */
//    public List<WeiBoBean> queryDataDependCurrentUserId(int currentUserId) {
//        return weiBoBeanDao.queryBuilder().where(WeiBoBeanDao.Properties.CurrentUserId.eq(currentUserId)).build().list();
//    }
//
//    /**
//     * 完成对数据库条件查询数据操作 FriendShipID
//     *
//     * @return
//     */
//    public List<WeiBoBean> queryDataDependFriendShipID(String friendShipID) {
//        return weiBoBeanDao.queryBuilder().where(WeiBoBeanDao.Properties.FriendshipId.eq(friendShipID)).build().list();
//    }

}
