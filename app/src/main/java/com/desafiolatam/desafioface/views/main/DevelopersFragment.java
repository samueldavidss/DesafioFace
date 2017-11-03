package com.desafiolatam.desafioface.views.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desafiolatam.desafioface.R;
import com.desafiolatam.desafioface.adapter.DevelopersAdapter;
import com.desafiolatam.desafioface.models.Developer;
import com.desafiolatam.desafioface.network.users.GetUsers;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopersFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private DevelopersAdapter adapter;
    private boolean pendingRequest= false;


    public DevelopersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_developers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Permite hacer scroll hacia abajo o scroll infinito


        refreshLayout = view.findViewById(R.id.reloadSrl);
        RecyclerView recyclerView = view.findViewById(R.id.developerRv);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DevelopersAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int position = linearLayoutManager.findLastVisibleItemPosition();
                int total = linearLayoutManager.getItemCount();

                if(total - 10 < position){
                    if(pendingRequest){
                        Map<String, String> map =  new HashMap<String, String>();
                        String currentPages = String.valueOf(((total/10) + 1));
                        map.put("page",currentPages);
                        new ScrollRequest(4).execute(map);


                    }
                }
            }
        });
    }

    private class ScrollRequest extends GetUsers{
        public ScrollRequest(int addicionalPages) {
            super(addicionalPages);
        }

        @Override
        protected void onPreExecute() {
         pendingRequest=true;
            refreshLayout.setRefreshing(true);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            pendingRequest=false;
            adapter.update();
            refreshLayout.setRefreshing(false);
        }
    }
    }

