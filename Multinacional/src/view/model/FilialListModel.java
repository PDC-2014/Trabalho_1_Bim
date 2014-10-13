package view.model;

import java.util.List;
import javax.swing.AbstractListModel;
import model.Filial;

public class FilialListModel extends AbstractListModel<Filial> {

    private List<Filial> filiais;

    public FilialListModel(List<Filial> filiais) {
        this.filiais = filiais;
    }

    @Override
    public int getSize() {
        try {
            return filiais.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Filial getElementAt(int index) {
        try {
            return filiais.get(index);
        } catch (Exception e) {
            return null;
        } 
    }
}
