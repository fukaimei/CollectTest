package net.fkm.collecttest.db;

import net.fkm.collecttest.db.model.MovieCollectDBModel;
import net.fkm.collecttest.utils.BaseUtil;
import net.fkm.collecttest.utils.L;
import net.fkm.collecttest.utils.ToastUtil;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Realm数据库帮助类
 */
public class RealmDBHelp {

    private Realm mRealm;
    private RealmAsyncTask mTransaction;

    public RealmDBHelp() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * 添加收藏数据到本地数据库
     *
     * @param mvId
     * @param mvName
     * @param movieImg
     * @param movieLabel
     * @param videoType
     */
    public void addCollectToDB(String mvId, String mvName, String movieImg, String movieLabel, String videoType) {

        MovieCollectDBModel movieCollectDBModel = new MovieCollectDBModel();
        movieCollectDBModel.setMovieId(mvId);
        movieCollectDBModel.setMovieName(mvName);
        movieCollectDBModel.setPoster(movieImg);
        movieCollectDBModel.setMovieLabel(movieLabel);
        movieCollectDBModel.setVideoType(videoType);
        movieCollectDBModel.setCreatedTime(BaseUtil.getNowDateTimeFormat());

        mTransaction = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(movieCollectDBModel);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                L.i("收藏数据添加成功！");
                ToastUtil.showToast(String.format("%s 已收藏", mvName));
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                L.i("收藏数据添加失败：" + error.toString());
                ToastUtil.showToast(String.format("%s 收藏失败", mvName));
            }
        });

    }

    /**
     * 查询所有的收藏数据
     *
     * @param listener
     */
    public void queryAllCollectData(RealmChangeListener<RealmResults<MovieCollectDBModel>> listener) {
        RealmResults<MovieCollectDBModel> movieCollectDBModels = mRealm.where(MovieCollectDBModel.class)
                .sort("createdTime", Sort.DESCENDING)
                .findAllAsync();
        movieCollectDBModels.addChangeListener(listener);
    }

    /**
     * 清除全部收藏的数据库内容
     */
    public void deleteAllCollectData() {

        RealmResults<MovieCollectDBModel> movieCollectDBModels = mRealm.where(MovieCollectDBModel.class)
                .findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (movieCollectDBModels.isEmpty()) {
                    ToastUtil.showToast("暂无收藏数据可删除");
                    return;
                }
                movieCollectDBModels.deleteAllFromRealm();
                ToastUtil.showToast("收藏数据已全部清空");
            }
        });

    }

    /**
     * 通过键值对来删除指定收藏数据
     *
     * @param key
     * @param value
     */
    public void deleteByKeyCollectData(String key, String value) {

        RealmResults<MovieCollectDBModel> movieCollectDBModels = mRealm.where(MovieCollectDBModel.class)
                .equalTo(key, value)
                .findAllAsync();
        movieCollectDBModels.addChangeListener(new RealmChangeListener<RealmResults<MovieCollectDBModel>>() {
            @Override
            public void onChange(RealmResults<MovieCollectDBModel> movieCollectDBModels1) {
                if (movieCollectDBModels1.isEmpty()) return;
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        movieCollectDBModels1.deleteFromRealm(0);
                    }
                });
            }
        });
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /**
     * 关闭事务
     */
    public void closeTransaction() {
        if (mTransaction != null && !mTransaction.isCancelled()) {
            mTransaction.cancel();
        }
    }

}
