package com.salemobile.essis.magnus;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.salemobile.essis.magnus.Adaptadores.ClienteListAdapter;
import com.salemobile.essis.magnus.Adaptadores.ProductosListAdapter;
import com.salemobile.essis.magnus.Entidades.Clientes;
import com.salemobile.essis.magnus.Entidades.Productos;
import com.salemobile.essis.magnus.EventsFragments.ClientesFragmentEvent;
import com.salemobile.essis.magnus.EventsFragments.ProductosFragmentEvent;
import com.salemobile.essis.magnus.EventsFragments.ProductosFragmentEventList;
import com.salemobile.essis.magnus.EventsFragments.Providers.BusProvider;
import com.salemobile.essis.magnus.dummy.DummyContent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductosFragment extends Fragment implements AbsListView.OnItemClickListener {
    private ListView lista;
    ProductosFragmentEvent evtFragment;
    ProductosFragmentEventList evtFragmentList;
    ArrayList<Productos> lista_productos = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;
    private ListAdapter mAdapter;
    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public ProductosFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BusProvider.getInstance().register(this);
        evtFragment = new ProductosFragmentEvent(new Productos());
        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        lista = (ListView)view.findViewById(android.R.id.list);
        escribirFicheroMemoriaInterna();
        String JSONText = LeerArchivo();
        try {
             lista_productos = TransformJSONtoObject(JSONText);
            Clientes yo;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lista.setAdapter(new ProductosListAdapter(lista_productos));
        evtFragmentList = new ProductosFragmentEventList(lista_productos);
        BusProvider.getInstance().post(evtFragmentList);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);
                final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity(), editText.getText(), Toast.LENGTH_LONG).show();
                                evtFragment = new ProductosFragmentEvent(lista_productos.get(position));
                                System.out.println("frag2, valor de txtFrag2: " + evtFragment);
                                BusProvider.getInstance().post(evtFragment);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();
        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }
    //Metodos Archivos
    private void escribirFicheroMemoriaInterna() {
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));
            fout.write("Texto ola ke ace");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
    }
    public String LeerArchivo(){
        String texto ="";
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "ALM2ALBERTO.magnus");
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));
            String cadena;
            while((cadena = fin.readLine())!=null) {
                System.out.println(cadena);
                texto += cadena;
            }
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
        }
        return  texto;
    }
    public ArrayList<Productos> TransformJSONtoObject(String JSONText) throws JSONException {
        String json = JSONText;
        ArrayList<Productos> lista_productos = new ArrayList<>(); //inicializamos la lista donde almacenaremos los objetos Fruta
        JSONObject object = new JSONObject(json); //Creamos un objeto JSON a partir de la cadena
        JSONArray json_array = object.optJSONArray("productos"); //cogemos cada uno de los elementos dentro de la etiqueta "frutas"
        for (int i = 0; i < json_array.length(); i++) {
            lista_productos.add(new Productos(json_array.getJSONObject(i))); //creamos un objeto Fruta y lo insertamos en la lista
        }
        return  lista_productos;
    }
    //

}
