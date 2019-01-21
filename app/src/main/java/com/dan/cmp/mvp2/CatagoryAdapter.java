package com.dan.cmp.mvp2;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.List;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.CategoryViewHolder>{

    private List<CategoryModel> list;

    public CatagoryAdapter(List<CategoryModel> list) {
        this.list = list;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {

        CategoryModel category = list.get(position);

        holder.textCategory.setText(category.cardTheme);

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
        return list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView textCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            textCategory = (TextView) itemView.findViewById(R.id.text_category);
        }
    }
}
