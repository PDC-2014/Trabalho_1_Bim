package model;

import java.io.Serializable;
import java.util.Objects;

public class ItemCompra implements Serializable{
    private Compra compra;
    private Produto produto;
    private Integer quantidade;

    public ItemCompra(Compra compra, Produto produto, Integer quantidade) {
        this.compra = compra;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemCompra() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.compra);
        hash = 13 * hash + Objects.hashCode(this.produto);
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
        final ItemCompra other = (ItemCompra) obj;
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }
}
