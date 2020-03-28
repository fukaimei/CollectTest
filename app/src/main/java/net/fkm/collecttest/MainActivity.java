package net.fkm.collecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.fkm.collecttest.adapter.MovieAdapter;
import net.fkm.collecttest.db.RealmDBHelp;
import net.fkm.collecttest.db.model.MovieCollectDBModel;
import net.fkm.collecttest.model.MovieDataModel;
import net.fkm.collecttest.utils.BaseUtil;
import net.fkm.collecttest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    private MovieAdapter adapter;
    private List<MovieDataModel.DataBean> mList = new ArrayList<>();
    private RealmDBHelp mRealmDBHelp;
    private boolean refreshType;

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.homeMovieList)
    RecyclerView homeMovieList;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRefresh();
    }

    private void initView() {
        ButterKnife.bind(this);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                // 跳转至我的收藏界面
                startActivity(new Intent(MainActivity.this, MovieCollectActivity.class));
            }
        });
        // 初始化本地数据库帮助类
        mRealmDBHelp = new RealmDBHelp();

    }

    private void initRefresh() {
        // 开启自动加载功能（非必须）
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshType = true;
                        // 加载数据
                        initData();
                        refreshLayout.finishRefresh();
                        refreshLayout.resetNoMoreData();//setNoMoreData(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshType = false;
                        ToastUtil.showToast("暂无更多的数据啦");
                        // 将不会再次触发加载更多事件
                        refreshLayout.finishLoadMoreWithNoMoreData();
                        refreshLayout.setEnableLoadMore(false);
                        refreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });
        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    private void initData() {

        // 模拟从网络获取到json数据  app\src\main\assets\movieData.json
        String jsonData = BaseUtil.getAssetsJson(this, "movieData.json");
//        L.i("jsonData：" + jsonData);
        if (TextUtils.isEmpty(jsonData)) {
            ToastUtil.showToast("Json数据为空");
            return;
        }
        if (mList != null) mList.clear();
        // 将json数据解析为Java实体对象
        MovieDataModel movieDataModel = new Gson().fromJson(jsonData, MovieDataModel.class);
        List<MovieDataModel.DataBean> dataBeanList = movieDataModel.getData();
        for (MovieDataModel.DataBean dataBean : dataBeanList) {
            MovieDataModel.DataBean data = new MovieDataModel.DataBean();
            data.setMvid(dataBean.getMvid());
            data.setName(dataBean.getName());
            data.setPoster(dataBean.getPoster());
            data.setUpdateStatus(dataBean.getUpdateStatus());
            data.setTag(dataBean.getTag());
            data.setVideo_type(dataBean.getVideo_type());
            mList.add(data);
        }

        // 查询本地收藏数据库中是否存在收藏的数据（更改收藏图标用的）
        queryAllCollectData();

        try {
            // 设置适配器
            homeMovieList.setLayoutManager(new GridLayoutManager(this, 3));
            adapter = new MovieAdapter(this, mList);
            homeMovieList.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置条目的点击事件
        adapter.setItemClikListener(new MovieAdapter.OnItemClikListener() {

            @Override
            public void onItemClik(View view, int position) {
                String mvId = mList.get(position).getMvid();
                String mvName = mList.get(position).getName();
                String movieImg = mList.get(position).getPoster();
                String movieTag = mList.get(position).getTag();
                String videoType = mList.get(position).getVideo_type();
                boolean isCollect = mList.get(position).isCollect();
                if (isCollect) {
                    // 如果已经收藏了则清除该条收藏
                    // 根据Id来删除一条数据
                    mRealmDBHelp.deleteByKeyCollectData("movieId", mvId);
                    mList.get(position).setCollect(false);
                    ToastUtil.showToast(String.format("%s 已取消收藏", mvName));
                } else {
                    // 添加收藏数据到本地数据库
                    mRealmDBHelp.addCollectToDB(mvId, mvName, movieImg, movieTag, videoType);
                    mList.get(position).setCollect(true);
                }
                // 通知适配器数据改变重新更新界面
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClik(View view, int position) {

            }

        });

    }

    /**
     * 查询本地收藏数据库中是否存在收藏的数据（更改收藏图标用的）
     */
    private void queryAllCollectData() {

        mRealmDBHelp.queryAllCollectData(new RealmChangeListener<RealmResults<MovieCollectDBModel>>() {
            @Override
            public void onChange(RealmResults<MovieCollectDBModel> movieCollectDBModels) {
                if (movieCollectDBModels.isEmpty()) {
                    return;
                }
                for (MovieCollectDBModel movieCollectDBModel : movieCollectDBModels) {
                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).getMvid().equals(movieCollectDBModel.getMovieId())) {
                            mList.get(i).setCollect(true);
                            break;
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mRealmDBHelp.closeTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmDBHelp.close();
    }
}
