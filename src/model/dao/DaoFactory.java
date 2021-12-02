package model.dao;

import model.dao.impl.SellerDaoJDBC;

// Classe auxiliar responsável por instanciar os 'Daos'.
// Vai ter operações estáticas para instanciar os 'Daos'.
public class DaoFactory {

    // Vai expor um método que retorna o tipo da interface, mas
    // internamente vai instanciar uma implementação. Esta é uma forma
    // de não precisar expor a implementação.
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC();
    }
}