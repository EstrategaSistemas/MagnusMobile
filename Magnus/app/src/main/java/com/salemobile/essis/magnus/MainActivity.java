package com.salemobile.essis.magnus;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.salemobile.essis.magnus.Adaptadores.ClienteListAdapter;
import com.salemobile.essis.magnus.BroadCastRecivers.ClientesReceiver;
import com.salemobile.essis.magnus.Entidades.Clientes;
import com.salemobile.essis.magnus.Entidades.Productos;
import com.salemobile.essis.magnus.EventsFragments.ClientesFragmentEvent;
import com.salemobile.essis.magnus.EventsFragments.ProductosFragmentEvent;
import com.salemobile.essis.magnus.EventsFragments.ProductosFragmentEventList;
import com.salemobile.essis.magnus.EventsFragments.Providers.BusProvider;
import com.squareup.otto.Subscribe;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

import static com.salemobile.essis.magnus.R.id.contenedor;
import static com.salemobile.essis.magnus.R.id.item_touch_helper_previous_elevation;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener,
        ClientesFragment.OnFragmentInteractionListener,ProductosFragment.OnFragmentInteractionListener {
    private ImageButton btnVentas,btnCuentas,btnStock,btnSiguiente,btnLiquidacion;
    private ClientesFragment ClientFragment;
    private CuentasFragment CuentasFrgmnt;
    private ProductosFragment ProductsFragment;
    private ClientesReceiver receiver;
    private ArrayAdapter<Clientes> adapter;
    List<String> ListaClientes = new ArrayList<String>();
    private Observable mObservers;
    // esta variable sera la que compartiran todos los fragment.
    private Object data;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BusProvider.getInstance().register(this);
        InicializarComponentes();
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.lsvProductos);
        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of datajxzknjnknvnxc

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });

    }
    private void InicializarComponentes() {
        btnVentas = (ImageButton) findViewById(R.id.ventas);
        btnCuentas = (ImageButton) findViewById(R.id.cuentas);
        btnStock = (ImageButton) findViewById(R.id.almacen);
        btnSiguiente = (ImageButton) findViewById(R.id.siguiente);
        btnLiquidacion = (ImageButton) findViewById(R.id.balance);
        btnVentas.setOnTouchListener(this);
        btnCuentas.setOnTouchListener(this);
        btnStock.setOnTouchListener(this);
        btnSiguiente.setOnTouchListener(this);
        btnLiquidacion.setOnTouchListener(this);



    }
    private void CargarFragmento(Fragment GenericFrgmnt) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(contenedor, GenericFrgmnt);
        ft.commit();
    }
    private void CargarFragmentoLateral(Fragment GenericFrgmnt) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.lateral, GenericFrgmnt);
        ft.commit();
    }
    public CuentasFragment getCuentasFrgmnt() {
        if(CuentasFrgmnt ==null)
        {CuentasFrgmnt = new CuentasFragment();}
        return CuentasFrgmnt;
    }
    public ClientesFragment getClientFragment() {
        if(ClientFragment ==null)
        {ClientFragment = new ClientesFragment();
        //    mObservers.addObserver((Observer) ClientFragment);
        }
        return ClientFragment;
    }
    public ProductosFragment getProductsFragment() {
        if(ProductsFragment ==null)
        {ProductsFragment = new ProductosFragment().newInstance("","");}
        return ProductsFragment;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
       ImageButton btn = (ImageButton) v;
        int actionMasked = event.getActionMasked();
        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                btn.setColorFilter(R.color.color_fondo);
                btn.invalidate();
                cambiarFragmento(btn);
                break;
            case MotionEvent.ACTION_UP:
                btn.clearColorFilter();
                btn.invalidate();
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                btn.setColorFilter((R.color.color_fondo));
                btn.invalidate();
                break;
        }
        return true;
    }
    private void cambiarFragmento(View view) {
        switch (view.getId())
        {
            case R.id.cuentas: CargarFragmento(getCuentasFrgmnt()); break;
            case R.id.balance:
                CargarFragmento(getClientFragment()); break;
            case R.id.siguiente: CargarFragmento(getProductsFragment()); break;
        }
    }
    @Override
    public void onFragmentInteraction(int id) {
    }
    @Override
    public void onFragmentInteraction(String id) {
    }
    @Override
    protected void onResume() {
        super.onResume();
        receiver = new ClientesReceiver(adapter);
        this.registerReceiver(receiver, new IntentFilter("seleccionarCliente"));
    }
    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(receiver);
    }
    public void updateFragments(Object data) {
        mObservers.notifyObservers(data);
    }
    @Subscribe
    public void ontextoFrag2Event(ClientesFragmentEvent txt) {
        System.out.println("frag1.ontextoFrag2Event: " + txt.valor);
        TextView txtv1 = (TextView)findViewById(
                R.id.lblTicket);
        String clientesConcatenados = "";
        ListaClientes.add(txt.valor.getNombre() + txt.valor.getCredito());
        for(String i :ListaClientes )
        {
            clientesConcatenados += " " +i;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ListaClientes);
        listView.setAdapter(adapter);
    }
    @Subscribe
    public void onProductosEvt(ProductosFragmentEvent txt) {
        System.out.println("frag1.ontextoFrag2Event: " + txt.valor);
        TextView txtv1 = (TextView)findViewById(
                R.id.lblTicket);

        String clientesConcatenados = "";
        ListaClientes.add(txt.valor.getDescripcion() + txt.valor.getTipoProducto() );
        for(String i :ListaClientes )
        {

            clientesConcatenados += " " +i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ListaClientes);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }
    @Subscribe
    public void onProductosEvtList(ProductosFragmentEventList txt) {
       Spinner mSpinner = (Spinner)findViewById(R.id.codigo);
        Spinner spNombre = (Spinner) findViewById(R.id.spNombre);
        ArrayList<String> options=new ArrayList<String>();
        ArrayList<String> nombres = new ArrayList<String>();
        options.add("");
        nombres.add("");
        for(Productos item: txt.valor)
       {
           options.add(Integer.toString(item.getIdProducto()));
           nombres.add(item.getDescripcion());
       }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        ArrayAdapter<String> adaptername = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        mSpinner.setAdapter(adapter);
        spNombre.setAdapter(adaptername);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });

        spNombre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }
    public void filtrar(){}
}

