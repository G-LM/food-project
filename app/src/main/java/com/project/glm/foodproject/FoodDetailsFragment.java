package com.project.glm.foodproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class FoodDetailsFragment extends Fragment implements View.OnClickListener {

    public static final String URL = "http://demo5337554.mockable.io";
    private static Food m_foodItem;

    public static FoodDetailsFragment newInstance(Food _item, int _index) {
        FoodDetailsFragment f = new FoodDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", _index);
        args.putParcelable("FOOD", _item);
        f.setArguments(args);
        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _bundle) {
        if (_container == null) {
            return null;
        }

        m_foodItem = getArguments().getParcelable("FOOD");
        View view = _inflater.inflate(R.layout.activity_food_detail, _container, false);

        TextView name = (TextView) view.findViewById(R.id.TV_detail_food_name);
        name.setText(m_foodItem.getId());
        TextView price = (TextView) view.findViewById(R.id.TV_detail_food_price);
        price.setText(Common.doubleToPrice(m_foodItem.getPrice()));
        ImageView pic = (ImageView) view.findViewById(R.id.IV_detail_food_pic);
        pic.setImageResource(m_foodItem.getPicId());
        TextView description = (TextView) view.findViewById(R.id.TV_detail_food_description);
        description.setText(m_foodItem.getDescription());

        ((Button) view.findViewById(R.id.B_order)).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View _view) {
        try {
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("FOOD_ID", m_foodItem.getId());

            Common.ProcessRequest process = new Common.ProcessRequest();
            process.execute(URL, jsonParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(getActivity(), "Merci!", Toast.LENGTH_LONG).show();
    }
}
