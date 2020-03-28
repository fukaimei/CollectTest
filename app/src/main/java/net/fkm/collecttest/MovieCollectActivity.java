package net.fkm.collecttest;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.fkm.collecttest.adapter.MovieCollectAdapter;
import net.fkm.collecttest.db.RealmDBHelp;
import net.fkm.collecttest.db.model.MovieCollectDBModel;
import net.fkm.collecttest.model.MovieCollectModel;
import net.fkm.collecttest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import top.limuyang2.customldialog.IOSMsgDialog;
import top.limuyang2.ldialog.base.OnDialogDismissListener;


public class MovieCollectActivity extends AppCompatActivity {

    private RealmDBHelp mRealmDBHelp;
    private List<MovieCollectModel> mList = new ArrayList<>();
    private MovieCollectAdapter adapter;
    private boolean refreshType;

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.movieCollectList)
    RecyclerView movieCollectList;
    @BindView(R.id.empty_view)
    RelativeLayout empty_view;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_collect);
        initView();
        initRefresh();
    }

    private void initView() {
        ButterKnife.bind(this);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                deleteRealmDB();
            }
        });
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
        queryAllCollectData();
    }

    /**
     * 查询本地收藏数据库中所有的数据
     */
    private void queryAllCollectData() {

        mRealmDBHelp.queryAllCollectData(new RealmChangeListener<RealmResults<MovieCollectDBModel>>() {
            @Override
            public void onChange(RealmResults<MovieCollectDBModel> movieCollectDBModels) {
                if (movieCollectDBModels.isEmpty()) {
                    return;
                }
                if (mList != null) mList.clear();
                for (MovieCollectDBModel movieCollectDBModel : movieCollectDBModels) {
                    MovieCollectModel data = new MovieCollectModel();
                    data.setMovieId(movieCollectDBModel.getMovieId());
                    data.setMovieName(movieCollectDBModel.getMovieName());
                    data.setPoster(movieCollectDBModel.getPoster());
                    data.setMovieTag(movieCollectDBModel.getMovieLabel());
                    data.setVideoType(movieCollectDBModel.getVideoType());
                    data.setCreatedTime(movieCollectDBModel.getCreatedTime());
                    mList.add(data);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parsingMovieList();
                    }
                });
            }
        });

    }

    private void parsingMovieList() {

        movieCollectList.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MovieCollectAdapter(this, mList);
        movieCollectList.setAdapter(adapter);

        if (mList.isEmpty()) {
            empty_view.setVisibility(View.VISIBLE);
            movieCollectList.setVisibility(View.GONE);
        } else {
            movieCollectList.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }

        adapter.setItemClikListener(new MovieCollectAdapter.OnItemClikListener() {
            @Override
            public void onItemClik(View view, int position) {
                // 根据Id来删除一条数据
                deleteByKeyCollectData(position);
            }

            @Override
            public void onItemLongClik(View view, int position) {

            }
        });
    }

    /**
     * 根据Id来删除一条数据
     *
     * @param position
     */
    private void deleteByKeyCollectData(int position) {

        IOSMsgDialog.Companion.init(getSupportFragmentManager())
                .setTitle("清除影视提示")
                .setMessage("是否要清除该条影视收藏的数据？")
                .setAnimStyle(R.style.LDialogScaleAnimation)
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 根据Id来删除一条数据
                        mRealmDBHelp.deleteByKeyCollectData("movieId", mList.get(position).getMovieId());
                        mList.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                }, Color.RED)
                .setDismissListener(new OnDialogDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                }).setCancelableOutside(true).show();
    }

    /**
     * 删除本地数据库中全部的收藏数据
     */
    private void deleteRealmDB() {

        IOSMsgDialog.Companion.init(getSupportFragmentManager())
                .setTitle("清空全部收藏数据提示")
                .setMessage("是否要立即清空全部我的收藏的数据？如果要清除单条数据的话请点击单条数据列表。")
                .setAnimStyle(R.style.LDialogScaleAnimation)
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 清除全部收藏的数据库内容
                        mRealmDBHelp.deleteAllCollectData();
                        mList.clear();
                        empty_view.setVisibility(View.VISIBLE);
                        movieCollectList.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();

                    }
                }, Color.RED)
                .setDismissListener(new OnDialogDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                }).setCancelableOutside(true).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmDBHelp.close();
    }

}
