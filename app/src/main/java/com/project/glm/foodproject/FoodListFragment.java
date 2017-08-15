package com.project.glm.foodproject;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by glm on 01/08/2017.
 */

public class FoodListFragment extends ListFragment {
    boolean m_dualPanel;
    int m_currentPosition = 0;

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        setListAdapter(new FoodListAdapter(getActivity(), R.layout.food_item, fillList()));

        View detailsFrame = getActivity().findViewById(R.id.details);
        m_dualPanel = detailsFrame != null;

        if (_savedInstanceState != null) {
            m_currentPosition = _savedInstanceState.getInt("currentChoice", 0);
        }
        if (m_dualPanel) {
            showDetails(m_currentPosition);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_food_list, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle _savedState) {
        super.onSaveInstanceState(_savedState);
        _savedState.putInt("currentChoice", m_currentPosition);
    }

    @Override
    public void onListItemClick(ListView _listView, View _view, int _position, long _id) {
        showDetails(_position);
    }

    void showDetails(int _index) {
        m_currentPosition = _index;
        Food item = (Food) getListView().getItemAtPosition(m_currentPosition);

        if (m_dualPanel) {
            getListView().setItemChecked(m_currentPosition, true);

            FoodDetailsFragment details = (FoodDetailsFragment) getFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != m_currentPosition) {
                details = FoodDetailsFragment.newInstance(item, m_currentPosition);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), FoodDetailsActivity.class);
            intent.putExtra("FOOD", item);
            startActivity(intent);
        }
    }

    private ArrayList<Food> fillList() {
        ArrayList<Food> foodList = new ArrayList<>();
        for(int i=0; i<10; ++i) {
            foodList.add(new Food("Tajine aubergine agneau", 20.00,
                    R.drawable.tajine_aubergine_agneau, R.drawable.tajine_aubergine_agneau_mini, "Decription : Tajine aubergine agneau"));
            foodList.add(new Food("Salade lentille grenade", 15.00,
                    R.drawable.salade_lentille_grenade, R.drawable.salade_lentille_grenade_mini, "Descrption : Salade lentille grenade" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nFelicitations ! Vous êtes a la fin de la description! "));
        }
        return foodList;
    }

}
