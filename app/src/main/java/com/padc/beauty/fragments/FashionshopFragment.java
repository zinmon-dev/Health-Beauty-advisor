package com.padc.beauty.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;

import com.padc.beauty.adapters.FashionShopAdapter;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Asus on 9/13/2016.
 */
public class FashionshopFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_fashionshop)
    RecyclerView rvfashionshop;

    private int gridColumnSpanCount=1;

    private FashionShopAdapter mFashionShopAdapter;
    private ArrayList<FashionShopVO> fashionShopList;

    public static FashionshopFragment newInstance(){
        FashionshopFragment fashionshopFragment = new FashionshopFragment();
        return fashionshopFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fashionshop, container, false);
        ButterKnife.bind(this, rootView);
        rvfashionshop.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        List<FashionShopVO> fashionshopList = FashionShopandBeautySaloonModel.getInstance().getFashionShopList();
        //showdata();
        mFashionShopAdapter = new FashionShopAdapter(fashionshopList);
        rvfashionshop.setAdapter(mFashionShopAdapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                mFashionShopAdapter.filter(searchQuery.toString().trim());
                rvfashionshop.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                //searchItem.setVisible(false);
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                //searchItem.setVisible(true);
                return true;  // Return true to expand action view
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.FahionShopandBeautySaloonDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<FashionShopVO> newFashionShopList = event.getFashionShopList();
        mFashionShopAdapter.setNewData(newFashionShopList);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.FASHION_SHOP_LIST_LOADER, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                BeautyContract.FashionshopEntry.CONTENT_URI,
                null,
                null,
                null,
                BeautyContract.FashionshopEntry.COLUMN_SHOP_ID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<FashionShopVO> fashionshopList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                FashionShopVO attraction = FashionShopVO.parseFromCursor(data);
                fashionshopList.add(attraction);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved fashionshop ASC : " + fashionshopList.size());
        mFashionShopAdapter.setNewData(fashionshopList);

        FashionShopandBeautySaloonModel.getInstance().setStoredData(fashionshopList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
