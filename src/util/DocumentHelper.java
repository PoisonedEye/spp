package util;

import entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentHelper{

    public static byte[] generateTransfer(DocumentWriter writer) throws IOException {
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Transfer.class);
        List transfers = criteria.list();
        writer.t("Transfers:").newLine();
        writer.h("Company").h("Time").h("Transferer").h("Product name").h("Product count").newLine();
        for (Object transfer : transfers) {
            Transfer t = (Transfer) transfer;
            writer
                    .v(t.getCompany().getName())
                    .v(t.getTime())
                    .v(t.getTransferer().getFullName());
            criteria = dbSession.createCriteria(Product.class).add(Restrictions.eq("transfer", t));
            List products = criteria.list();
            Map<String, Integer> types = new HashMap<>();
            for (Object product : products) {
                String type = ((Product) product).getProductType().getFullModelName();
                types.put(type, types.get(type) == null ? 1 : types.get(type) + 1);
            }
            for (String type:types.keySet()){
                writer.newLine().v("").v("").v("")
                        .v(type)
                        .v(types.get(type).toString())
                        .newLine();
            }
            writer.newLine();
        }
        writer.flush();
        return writer.getBytes();
    }

    public static byte[] generateReceiving(DocumentWriter writer) throws IOException{
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Recieving.class);
        List receivings = criteria.list();
        writer.t("Receivings:").newLine();
        writer.h("Company").h("Time").h("Receiver").h("Product name").h("Product count").newLine();
        for (Object receiving : receivings) {
            Recieving t = (Recieving) receiving;
            writer
                    .v(t.getCompany().getName())
                    .v(t.getTime())
                    .v(t.getReciever().getFullName());
            criteria = dbSession.createCriteria(Product.class).add(Restrictions.eq("recieving", t));
            List products = criteria.list();
            Map<String, Integer> types = new HashMap<String, Integer>();
            for (Object product : products) {
                String type = ((Product) product).getProductType().getFullModelName();
                types.put(type, types.get(type) == null ? 1 : types.get(type) + 1);
            }
            for (String type:types.keySet()){
                writer.newLine().v("").v("").v("")
                        .v(type)
                        .v(types.get(type).toString())
                        .newLine();
            }
            writer.newLine();
        }
        writer.flush();
        return writer.getBytes();
    }

    public static byte[] generateAcceptors(DocumentWriter writer)throws IOException{
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Position position = (Position) dbSession.createCriteria(Position.class).
                add(Restrictions.eq("name", "Acceptor")).uniqueResult();
        Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("position", position));
        List acceptors = criteria.list();
        writer.t("Acceptors:").newLine();
        writer.h("Full name").h("Login").h("Phone number").h("Taking office").h("shifts count").newLine();
        for (Object acceptor : acceptors) {
            Employee e = (Employee) acceptor;
            criteria = dbSession.createCriteria(AcceptorShift.class);
            Integer count = criteria.add(Restrictions.eq("acceptor", e)).list().size();
            if (!e.getFired()) {
                writer
                        .v(e.getFullName())
                        .v(e.getLogin())
                        .v(e.getPhoneNumber())
                        .v(e.getTakingOffice())
                        .v(count.toString())
                        .newLine();
            }
        }
            writer.flush();
            try{
                return writer.getBytes();
            }
            finally {
                dbSession.close();
            }
    }

    public static byte[] generateAcceptorShifts(DocumentWriter writer)throws IOException{
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(AcceptorShift.class);
        List shifts = criteria.list();
        writer.t("Acceptor shifts:").newLine();
        writer.h("Acceptor name").h("Beginning").h("Ending").h("Visited cell").h("Is product taken").h("Product name").newLine();
        for (Object shift : shifts) {
            AcceptorShift s = (AcceptorShift) shift;
            criteria = dbSession.createCriteria(CellVisiting.class);
            List visitings = criteria.add(Restrictions.eq("shift", s)).list();
            writer.v(s.getAcceptor().getFullName());
            writer.v(s.getBegining());
            writer.v(s.getEnding());

            for (Object visiting : visitings) {
                CellVisiting v = (CellVisiting) visiting;
                writer.newLine().v("").v("").v("");
                writer
                        .v(v.getCell().getNumber())
                        .v(v.getProductTaken() ? "Yes" : "No")
                        .v(v.getProduct().getProductType().getFullModelName());
            }
            writer.newLine();
        }
        writer.flush();
        try{
            return writer.getBytes();
        }
        finally {
            dbSession.close();
        }
    }

    public static byte[] generateCells(DocumentWriter writer)throws IOException{
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Cell.class);
        List cells = criteria.list();
        writer.t("Cells:").newLine();
        writer.h("Cell number").h("Product name").h("Barcode").h("Putting time").h("Acceptor").newLine();
        for (Object cell : cells) {
            Cell c = (Cell) cell;
            criteria = dbSession.createCriteria(CellVisiting.class);
            List visitings = criteria.add(Restrictions.eq("cell", c)).list();
            writer.v(c.getNumber());
            for (Object visiting : visitings) {
                CellVisiting v = (CellVisiting) visiting;
                if (!v.getProductTaken()) {
                    writer.newLine().v("");
                    writer
                            .v(v.getProduct().getProductType().getFullModelName())
                            .v(v.getProduct().getProductType().getBarcode().toString())
                            .v(v.getTime())
                            .v(v.getShift().getAcceptor().getFullName());
                }
            }
            writer.newLine();
        }
        writer.flush();
        try{
            return writer.getBytes();
        }
        finally {
            dbSession.close();
        }
    }
}
