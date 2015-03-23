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

//
//
//    //para detectar los clicks
//
//
//    final CaldroidListener listener = new CaldroidListener() {
//
//        @Override
//        public void onSelectDate(Date date, View view) {
//            Toast.makeText(getActivity(), formatter.format(date),
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onChangeMonth(int month, int year) {
//            String text = "month: " + month + " year: " + year;
//            Toast.makeText(getActivity(), text,
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onLongClickDate(Date date, View view) {
//            Toast.makeText(this
//                    ,
//                    "Long click " + formatter.format(date),
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCaldroidViewCreated() {
//            Toast.makeText(getActivity() ,
//                    "Caldroid view is created",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//    };
//
//    //caldroidFragment.setCaldroidListener(listener);
//    CalendarFragment.setCaldroidListener(listener);

}
