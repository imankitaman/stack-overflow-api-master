package ankit.com.taskmaster.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import ankit.com.taskmaster.R;
import ankit.com.taskmaster.models.Items;
import ankit.com.taskmaster.models.Tag;
import ankit.com.taskmaster.network.NetworkManager;
import ankit.com.taskmaster.uiutils.MyProgressDialog;
import ankit.com.taskmaster.uiutils.SpaceItemDecoration;
import ankit.com.taskmaster.view.adapters.TagsAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Items<Tag>> {

    private static final String TAG = MainActivity.class.getCanonicalName();
    @Bind(R.id.recycleTagsView)
    RecyclerView recycleTagsView;
    Call<Items<Tag>> call;
    private TagsAdapter adapter;
    private MyProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecycleView();
        progressDialog = new MyProgressDialog(this);
        NetworkManager networkmanager = new NetworkManager();
        call=networkmanager.loadPopularTags();
        call.enqueue(this);
        progressDialog.showProgressDialog(TAG,false);
    }


    private void initRecycleView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recycleTagsView.setLayoutManager(layoutManager);
        recycleTagsView.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new TagsAdapter();
        recycleTagsView.setAdapter(adapter);
    }


    @Override
    public void onResponse(Call<Items<Tag>> call, Response<Items<Tag>> response) {
        adapter.clearTagItems();
        adapter.setTagItems(response.body().getItems());
        adapter.notifyDataSetChanged();
        progressDialog.cancel();
    }

    @Override
    public void onFailure(Call<Items<Tag>> call, Throwable t) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (call != null)
            call.cancel();
    }

}
