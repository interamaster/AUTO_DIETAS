package com.mio.jrdv.auto_dietas;

import com.mio.jrdv.auto_dietas.data.CaldroidSampleCustomAdapter;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

/**
 * Created by joseramondelgado on 17/03/15.
 */
public class CalendarFragment extends CaldroidFragment {

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub
        return new CaldroidSampleCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }
}
