package edu.temple.mikem.checkerboardmaker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements CheckerboardDisplay.OnFragmentInteractionListener, GridSelector.OnFragmentInteractionListener{

    boolean twoPanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //determine if only one or two panes are visible
        twoPanes = (findViewById(R.id.checkerboard_display) != null);

        Fragment selector = new GridSelector();

        loadFragment(R.id.grid_selector, selector, false);

        if(twoPanes) {
            CheckerboardDisplay board = new CheckerboardDisplay();
            Bundle bundle = new Bundle();
            bundle.putInt(CheckerboardDisplay.dataKey, 0);
            board.setArguments(bundle);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onButtonPressed(int colums){
        CheckerboardDisplay board = new CheckerboardDisplay();

        Bundle bundle = new Bundle();
        bundle.putInt(CheckerboardDisplay.dataKey,colums);
        board.setArguments(bundle);

        loadFragment(twoPanes ? R.id.checkerboard_display : R.id.grid_selector, board, !twoPanes);
    }

    public void loadFragment(int paneId, Fragment fragment , boolean backStackPosition){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(paneId,fragment);

        if(backStackPosition){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
