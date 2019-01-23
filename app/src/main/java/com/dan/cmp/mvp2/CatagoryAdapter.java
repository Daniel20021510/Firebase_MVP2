package com.dan.cmp.mvp2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.CategoryViewHolder>{

    private List<CategoryModel> list;
    private Set<String> langSet;
    private List<String> unicLang;
    private HashMap<String,HashMap<String, List<String>>> map;
    private HashMap<String, List<String>> h1;
    private List<String> l1;
    private Context context;


    public CatagoryAdapter(List<CategoryModel> list, Context context) {
        this.context = context;

        this.list = list;
        List<String> listLang = new ArrayList<>();

        for(CategoryModel model: list) {
            listLang.add(model.language);
        }

        this.langSet = new HashSet<>(listLang);
        this.unicLang = new ArrayList<>(langSet);

/*
        for(int i = 0; i < list.size(); i++){
            CategoryModel category = list.get(i);
            l1.add(category.word);
            h1.put(category.cardTheme, l1);
            map.put("Language",h1);
        }*/
    }



    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        //надо сделать так чтобы язык тображался только один раз
        /*HashMap<String,List<String>> language = map.get("Language");
        Set keys = language.keySet();*/
        /*CategoryModel category = list.get(position);
        holder.textCategory.setText(category.language);
*/
        holder.textCategory.setText(unicLang.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> listCat = new ArrayList<>();
                final String currentLanguage = unicLang.get(position);

                for(CategoryModel model: list) {

                    if (model.language.equals(currentLanguage)) {
                        listCat.add(model.cardTheme);
                    }
                }

                HashSet<String> themSet = new HashSet<>(listCat);
                List<String> res = new ArrayList<>(themSet);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose category");

                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, res);
                //ArrayAdapter<List<String>> adapter = new ArrayAdapter<List<String>>(context, android.R.layout.simple_list_item_1, res);

                HashSet<String> prome = new HashSet<>(listCat);
                final List<String> li = new ArrayList<>(prome);
                String[] li2 = new String[li.size()];
                for(int i = 0;  i < li.size(); i ++){
                    li2[i] = li.get(i);
                }
                builder.setItems(li2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String currentTheme = li.get(which);
                        List<String> listCard = new ArrayList<>();
                        String currentLanguage = unicLang.get(position);
                        for(CategoryModel model: list) {

                            if (model.language.equals(currentLanguage) && model.cardTheme.equals(currentTheme)) {
                                listCard.add(model.word);
                            }
                        }
                        Toast toast = Toast.makeText(context,
                                listCard.get(0),
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }


                });

/*
                builder.setAdapter(adapter, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<String> listCard = new ArrayList<>();
                        String currentLanguage = unicLang.get(position);
                        String currentTheme = listCat.get(which);
                        for(CategoryModel model: list) {

                            if (model.language == currentLanguage && model.cardTheme == currentTheme) {
                                listCard.add(model.word);
                            }
                        }
                        Toast toast = Toast.makeText(context,
                                listCard.get(0),
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                });*/

// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "Удалить");
                menu.add(holder.getAdapterPosition(), 1, 0, "bpvtybnm");
            }
        });

    }

    @Override
    public int getItemCount() {
        return unicLang.size();
        //return list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView textCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            textCategory = (TextView) itemView.findViewById(R.id.text_category);
        }
    }
}
