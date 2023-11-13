package com.example.gestorDePedidosHibernate.controllers;

import com.example.gestorDePedidosHibernate.App;
import com.example.gestorDePedidosHibernate.domain.Sesion;
import com.example.gestorDePedidosHibernate.domain.item.Item;
import com.example.gestorDePedidosHibernate.domain.pedido.Pedido;

import com.example.gestorDePedidosHibernate.domain.pedido.PedidoDAO;
import com.example.gestorDePedidosHibernate.domain.usuario.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PedidosController implements Initializable {

    PedidoDAO pedidoDAO;

    @javafx.fxml.FXML
    private Label labelNombre;
    @javafx.fxml.FXML
    private TableView<Pedido> tablaPedidos;

    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cUsuario;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cTotal;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cId;
    @javafx.fxml.FXML
    private Button btnLogout;
    @javafx.fxml.FXML
    private BorderPane ventana;
    @javafx.fxml.FXML
    private Button btnEliminar;
    @javafx.fxml.FXML
    private Button btnDetalles;
    @javafx.fxml.FXML
    private Button btnEditar;
    @FXML
    private Button btnAniadir;

    @Override
    public void initialize( URL url , ResourceBundle resourceBundle ) {

        pedidoDAO = new PedidoDAO( );

        Sesion.setEsUnNuevoProducto( false );
        rellenarTabla( );

        tablaPedidos.getSelectionModel( ).selectedItemProperty( ).addListener( ( observableValue , pedido , t1 ) -> {
            Sesion.setPedidoPulsado( t1 );
        } );

    }

    private void rellenarTabla( ) {
        Usuario usuario = Sesion.getUsuarioActual( );
        pedidoDAO = new PedidoDAO( );
        List<Pedido> pedidosDeUser = pedidoDAO.pedidosDeUnUsuario( usuario );

        //Cambiar Titulo
        labelNombre.setText( "Pedidos de " + usuario.getNombreusuario( ) + " (" + usuario.getEmail( ) + ")" );
        //Rellenar la tabla
        cId.setCellValueFactory( ( fila ) -> {
            Long id = fila.getValue( ).getId_pedido( );
            return new SimpleStringProperty( id.toString( ) );
        } );
        cFecha.setCellValueFactory( ( fila ) -> {
            String cantidad = fila.getValue( ).getFecha( );
            return new SimpleStringProperty( cantidad );
        } );
        cUsuario.setCellValueFactory( ( fila ) -> {
            String nombre = Sesion.getUsuarioActual( ).getNombreusuario( );
            return new SimpleStringProperty( nombre );
        } );
        cTotal.setCellValueFactory( ( fila ) -> {
            String total = fila.getValue( ).getTotal( );
            return new SimpleStringProperty( total );
        } );
        ObservableList<Pedido> observableList = FXCollections.observableArrayList( );
        observableList.addAll( pedidosDeUser );
        tablaPedidos.setItems( observableList );
    }

    @javafx.fxml.FXML
    public void logoutButton( ) {
        Sesion.logout();
        App.loadFXML( "login-view.fxml" , "Iniciar Sesión" );
    }

    @javafx.fxml.FXML
    public void detalles( ) {
        if (Sesion.getPedidoPulsado( ) != null) App.loadFXML( "detallesPedido-view.fxml" , "Detalles del pedido" );
    }

    @javafx.fxml.FXML
    public void editar( ) {
        if(Sesion.getPedidoPulsado() != null){
            Sesion.setEsUnNuevoPedido( false );
            App.loadFXML( "editarPedido-view.fxml" , "Editar pedido" );
        }
    }

    @FXML
    public void eliminar( ) {
        if (Sesion.getPedidoPulsado( ) != null) {
            pedidoDAO.delete( Sesion.getPedidoPulsado( ) );
            this.rellenarTabla( );
        }
    }

    @FXML
    public void aniadir( ) {
        Sesion.setEsUnNuevoPedido( true );
        App.loadFXML( "editarPedido-view.fxml" , "Añadir pedido" );
    }
}
