//package com.hexabinome.saladetomateoignon.fragment.cantinder;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//
//import com.hexabinome.saladetomateoignon.R;
//import com.lorentzos.flingswipe.SwipeFlingAdapterView;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link SwipeTestFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// */
//public class SwipeTestFragment extends Fragment {
//
//    private OnFragmentInteractionListener mListener;
//    private ArrayList<String> elements;
//    private ArrayAdapter<String> arrayAdapter;
//    private SwipeFlingAdapterView flingContainer;
//    private View myView;
//
//    public SwipeTestFragment() {
//        //TODO remplacer ici par une liste de restaurants
//        elements = new ArrayList<>();
//        elements.add("php");
//        elements.add("c");
//        elements.add("python");
//        elements.add("java");
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        myView = inflater.inflate(R.layout.fragment_swipe_test, container, false);
//
//        initFling();
//        return inflater.inflate(R.layout.fragment_swipe_test, container, false);
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }
//
//    private void initFling()
//    {
//        //add the view via xml or programmatically
//        flingContainer = (SwipeFlingAdapterView) myView.findViewById(R.id.swipe);
//        //choose your favorite adapter
//        //TODO remplacer par un custom adaptater
//        arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
//                R.layout.item_swipe, R.id.textSwipe, elements);
//        flingContainer.setAdapter(arrayAdapter);
//
//        //implémente les méthode du fling
//        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
//            @Override
//            public void removeFirstObjectInAdapter() {
//                // this is the simplest way to delete an object from the Adapter (/AdapterView)
//                Log.d("LIST", "removed object!");
//                elements.remove(0);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onLeftCardExit(Object dataObject) {
//                //Do something on the left!
//                //You also have access to the original object.
//                //If you want to use it just cast it (String) dataObject
//            }
//
//            @Override
//            public void onRightCardExit(Object dataObject) {
//
//            }
//
//            @Override
//            public void onAdapterAboutToEmpty(int itemsInAdapter) {
//                // Ask for more data here
//                /*elements.add("XML ".concat(String.valueOf(i)));
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;*/
//            }
//
//            @Override
//            public void onScroll(float var1) {
//
//            }
//        });
//
//        Button oui = (Button) myView.findViewById(R.id.bouttonOui);
//        oui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flingContainer.getTopCardListener().selectRight();
//            }
//        });
//
//        Button non = (Button) myView.findViewById(R.id.bouttonNon);
//        non.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flingContainer.getTopCardListener().selectLeft();
//            }
//        });
//    }
//
//}
