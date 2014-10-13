package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("serial")
public class Compra implements Serializable{
    private Integer id;
    private Cliente cliente;
    private Date data;
    private List<ItemCompra> itens;

    public Compra(Cliente cliente, List<ItemCompra> itens) {
        this.cliente = cliente;
        this.itens = itens;
        this.data = new Date();
    }

    public Compra() {
    }
    
    public Double valorTotal() {
        Double total = 0D;
        
        for (ItemCompra i : itens)
            total += i.getProduto().getPrecoUnitario() * i.getQuantidade();
        
        return total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
