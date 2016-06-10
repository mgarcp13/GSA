package gsa.Interfaz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import gsa.gsa.Trabajadores;

/**
 * Created by mario on 9/06/16.
 */
public class MovieTouchHelperTrabajadores   extends ItemTouchHelper.SimpleCallback {
    private TrabajadoresAdapter mMovieAdapter;
    private Context c;

    public MovieTouchHelperTrabajadores(TrabajadoresAdapter movieAdapter, Context c){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.c = c;
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //TODO: Not implemented here
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //Remove item
        mMovieAdapter.remove(viewHolder.getAdapterPosition(), c);

    }

}
