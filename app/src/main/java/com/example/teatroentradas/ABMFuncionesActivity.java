package com.example.teatroentradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teatroentradas.controllers.FuncionController;
import com.example.teatroentradas.controllers.ObraController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ABMFuncionesActivity extends AppCompatActivity {

    Spinner spObra;
    Spinner spDia;
    Spinner spHora;
    Spinner spFuncion;
    AdminSQLiteOpenHelper adminSQL;
    List<Obra> obras = new ArrayList<>();
    List<Funcion> funciones = new ArrayList<>();
    ArrayAdapter<Obra> ObrasAdapter;
    ArrayAdapter<String> diasAdapter;
    ArrayAdapter<String> horasAdapter;
    ArrayAdapter<Funcion> funcionesAdapter;
    List<LocalDate> fechasFuncion;
    CheckBox editar;
    CheckBox eliminar;
    ObraController obraController = new ObraController(this);
    FuncionController funcionController = new FuncionController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmfunciones);
        adminSQL = new AdminSQLiteOpenHelper(this, "BDD_entradas_teatro", null, 1);
        spObra = findViewById(R.id.sp_obra);
        spDia = findViewById(R.id.sp_dia);
        spHora = findViewById(R.id.sp_hora);
        editar = findViewById(R.id.check_editar);
        eliminar = findViewById(R.id.check_eliminar);
        spFuncion = findViewById(R.id.sp_funcion);
        funciones = new ArrayList<>();
        listarObras();
        listarFunciones();

        ObrasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obras);
        ObrasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spObra.setAdapter(ObrasAdapter);

        funcionesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, funciones);
        funcionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFuncion.setAdapter(funcionesAdapter);

        diasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Seleccionar día", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"});
        diasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDia.setAdapter(diasAdapter);

        horasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Seleccionar hora", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00"});
        horasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHora.setAdapter(horasAdapter);

        editar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spDia.setEnabled(false);
                } else {
                    spObra.setEnabled(true);
                    spDia.setEnabled(true);
                }
            }
        });

        eliminar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spDia.setEnabled(false);
                    spHora.setEnabled(false);
                } else {
                    spObra.setEnabled(true);
                    spDia.setEnabled(true);
                    spHora.setEnabled(true);
                }
            }
        });

    }


    public void listarObras() {
        obras.clear();
        try {
            obras.addAll(obraController.getObras());
            obras.add(0, new Obra(0, "Seleccionar obra"));
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
        if (ObrasAdapter != null) {
            ObrasAdapter.notifyDataSetChanged();
        }
    }

    public void listarFunciones() {
        funciones.clear();
        try {
            funciones.clear();
            funciones.add(0, new Funcion(0, "Seleccionar funcion"));
            funciones.addAll(funcionController.getFunciones());
            spFuncion.setSelection(0);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
        if (ObrasAdapter != null) {
            ObrasAdapter.notifyDataSetChanged();
        }
    }


    public Obra setFuncion() {
        Obra obra = new Obra();
        List<Funcion> funciones = new ArrayList<>();

        // Obtengo obra del spinner
        Obra obraSeleccionada = (Obra) spObra.getSelectedItem();
        obra.setId(obraSeleccionada.getId());
        obra.setInicio(obraSeleccionada.getInicio());
        obra.setFin(obraSeleccionada.getFin());

        if (obraSeleccionada == null || obra.getId() == 0) {
            Toast.makeText(this, "Selecciona una obra", Toast.LENGTH_SHORT).show();
        }

        // Obtengo hora
        String horaSeleccionada = (String) spHora.getSelectedItem();

        // Obtengo día
        String diaSeleccionado = (String) spDia.getSelectedItem();
        DayOfWeek day = funcionController.obtenerDia(diaSeleccionado);

        // Obtengo todas las fechas para ese día y horario entre la fecha de inicio y fin
        fechasFuncion = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            fechasFuncion = funcionController.getFechasFunciones(LocalDate.of(obra.getInicio().getYear(), obra.getInicio().getMonth(), obra.getInicio().getDayOfMonth()), LocalDate.of(obra.getFin().getYear(), obra.getFin().getMonth(), obra.getFin().getDayOfMonth()), day);
        }

        // armo lista de funcioones
        for (LocalDate fecha : fechasFuncion) {
            Funcion func = new Funcion();
            func.setEntradasDisponibles(100);
            func.setDia(diaSeleccionado.toString());
            func.setHora(horaSeleccionada.toString());
            func.setFecha(fecha.toString());
            funciones.add(func);
        }

        // Agrego a lista de obra
        obra.setFunciones(funciones);
        return obra;
    }

    public void cargarFuncion(View view) {
        if(spObra.getSelectedItemPosition() > 0) {
            Obra obra = setFuncion();

            if (obra != null && spObra.getSelectedItemPosition() > 0 && spDia.getSelectedItemPosition() > 0 && spHora.getSelectedItemPosition() > 0) {
                funcionController.altaFuncion(obra);
                Toast.makeText(this, "Funciones creadas con éxito", Toast.LENGTH_SHORT).show();
                listarFunciones();
                spObra.setSelection(0);
                spDia.setSelection(0);
                spHora.setSelection(0);
                spFuncion.setSelection(0);
            } else {
                Toast.makeText(this, "seleccionar todos los campos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Seleccionar obra", Toast.LENGTH_SHORT).show();

        }
    }

    public void editarFuncion(View view) {
        if(spObra.getSelectedItemPosition() > 0 && spFuncion.getSelectedItemPosition() > 0 && spHora.getSelectedItemPosition() > 0) {
        if (editar.isChecked()) {
            Funcion funcionSeleccionada = (Funcion) spFuncion.getSelectedItem();
            int id = funcionSeleccionada.getId();
            String horaSeleccionada = (String) spHora.getSelectedItem();
            if (id != 0) {
                funcionController.editarFuncion(horaSeleccionada, id);
                listarFunciones();
                Toast.makeText(this, "Horario actualizado con éxito", Toast.LENGTH_SHORT).show();
                spFuncion.setSelection(0);
                spObra.setSelection(0);
                spDia.setSelection(0);
                spHora.setSelection(0);
                editar.setChecked(false);
                eliminar.setChecked(false);
            } else {
                Toast.makeText(this, "Debe marcar la opción editar", Toast.LENGTH_SHORT).show();
            }
        }
        }else {
            Toast.makeText(this, "Seleccionar obra, funcion y nuevo horario", Toast.LENGTH_SHORT).show();
        }
    }


    public void eliminarFuncion(View view){
        if (eliminar.isChecked()) {
            Funcion funcionSeleccionada = (Funcion) spFuncion.getSelectedItem();
            int id = funcionSeleccionada.getId();
            int filasEliminadas = funcionController.eliminarFuncion(id);
            this.listarFunciones();
            if (filasEliminadas == 1) {
                Toast.makeText(this, "Funcion eliminada con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No se pudo eliminar la Funcion", Toast.LENGTH_LONG).show();
            }
            spObra.setSelection(0);
            spDia.setSelection(0);
            spHora.setSelection(0);
            editar.setChecked(false);
            eliminar.setChecked(false);
        }
    }

}
