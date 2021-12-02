package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

    // Vamos criar uma dependência com a conexão, para não
    // termos que ficar usando conn em todos os métodos.
    // Vamos ter esse objeto conn a disposição em qualquer lugar da
    // classe SellerDaoJDBC.
    private Connection conn;

    // Vamos forçar a injeção de dependência, criando um construtor
    // que recebe um conn como parâmetro.
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Seller obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE seller.Id = ?");
            // A primeira interrogação recebe o 'id' que chegou como parâmetro na função.
            st.setInt(1, id);
            rs = st.executeQuery();
            // O 'rs' traz um objeto no formato de tabela, mas vamos ter que
            // transformar essa tabela em objeto.
            // rs.next testa se veio algum resultado, se a consulta não retornou
            // nenhum registro o rs da falso e pula para o return null, porém se
            // existir registro significa que retornou uma tabela.
            // Vamos então transformar essa tabela em objeto e retornar ese objeto.
            if (rs.next()) {
                // Instanciamos um departamento para depois setar os valores dele.
                Department dep = new Department();
                // Setamos o 'id' do objeto dep. Usamos o rs.getInt, pois 'id' é um número e
                // colocamos o nome da coluna onde ele está.
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                // Agora vamos instanciar um vendedor para depois setar os valores dele.
                Seller obj = new Seller();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                obj.setEmail(rs.getString("Email"));
                obj.setBaseSalary(rs.getDouble("BaseSalary"));
                obj.setBirthDate(rs.getDate("BirthDate"));
                obj.setDepartment(dep);
                return obj;
            }
            return null;
        }
        catch (SQLException e) {
            // Caso ocorra alguma exceção vamos lançar a nossa exceção
            // personalizada passando a msg da exceção original.
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}