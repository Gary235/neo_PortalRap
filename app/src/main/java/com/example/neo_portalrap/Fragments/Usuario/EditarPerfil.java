package com.example.neo_portalrap.Fragments.Usuario;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class EditarPerfil extends Fragment {

    androidx.appcompat.widget.Toolbar toolbar;

    TextInputLayout txtLay_Nombre, txtLay_Mail, txtLay_pass;
    TextInputEditText txtField_Nombre, txtField_Mail, txtField_pass;

    TextView titu_Nombre, titu_Mail;

    Button btn_CambiarFoto, btn_Guardar;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ayuda_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Salir");
                mensaje.setMessage("Los cambios no se efectuaran");
                mensaje.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
                mensaje.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.toUsuario();
                    }
                });
                mensaje.create();
                mensaje.show();
                break;

            case R.id.home_ayuda:
                AlertDialog.Builder mensaje_ayuda;
                mensaje_ayuda = new AlertDialog.Builder(getActivity());
                mensaje_ayuda.setTitle("Editar Perfil");
                mensaje_ayuda.setMessage("hola");
                mensaje_ayuda.setPositiveButton("Ok",null);
                mensaje_ayuda.create();
                mensaje_ayuda.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editar_perfil, container, false);


        toolbar = v.findViewById(R.id.toolbar_editar);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtLay_Nombre=v.findViewById(R.id.txtlayout_nombre_editar);
        txtField_Nombre = v.findViewById(R.id.txtfield_nombre_editar);
        titu_Nombre = v.findViewById(R.id.txt_tit_nombre_editar);
        txtField_Nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0)
                {
                    titu_Nombre.setText("Gary");
                }   else {
                    titu_Nombre.setText(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        txtLay_Mail=v.findViewById(R.id.txtlayout_mail_editar);
        txtField_Mail = v.findViewById(R.id.txtfield_mail_editar);
        titu_Mail = v.findViewById(R.id.txt_tit_mail_editar);
        txtField_Mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0)
                {
                    titu_Mail.setText("berkman.gary@gmail.com");
                }   else {
                    titu_Mail.setText(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtLay_pass = v.findViewById(R.id.txtlayout_contraseña_editar);
        txtField_pass = v.findViewById(R.id.txtfield_contraseña_editar);

        btn_Guardar = v.findViewById(R.id.btn_guardar);
        btn_CambiarFoto = v.findViewById(R.id.btnCambiarFoto);

        btn_Guardar.setOnClickListener(a -> {

            AlertDialog.Builder mensaje;
            mensaje = new AlertDialog.Builder(getActivity());
            mensaje.setTitle("Guardar Cambios");
            mensaje.setMessage("Los cambios se guardaran");
            mensaje.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
            mensaje.setPositiveButton("Guardar", (dialog, which) -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toUsuario();
            });
            mensaje.create();
            mensaje.show();

        });


        return v;
    }
}