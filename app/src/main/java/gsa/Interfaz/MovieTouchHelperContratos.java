package gsa.Interfaz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by mario on 10/06/16.
 */
public class MovieTouchHelperContratos extends ItemTouchHelper.SimpleCallback {

    private ContratosAdapter mMovieAdapter;
    private Context c;

    public MovieTouchHelperContratos(ContratosAdapter movieAdapter, Context c) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.c = c;
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mMovieAdapter.remove(viewHolder.getAdapterPosition(), c);

    }
}
