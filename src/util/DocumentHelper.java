package util;

import entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DocumentHelper{

    public static byte[] generateTransfer(DocumentWriter writer){
        List transfers = ServiceUtil.getDataService().getData(Transfer.class);
        writer.t("TRANSFERS:").newLine();
        writer.h("COMPANY").h("TIME").h("TRANSFERER").newLine();
        for (int i = 0; i < transfers.size(); i++){
            Transfer t =  (Transfer)transfers.get(i);
            writer
                .v(t.getCompany().getName())
                .v(t.getTime())
                .v(t.getTransferer().getFullName());
        }
        writer.flush();
        return writer.getBytes();
    }

    public static byte[] generateReceiving(DocumentWriter writer){
        List receivings = ServiceUtil.getDataService().getData(Recieving.class);
        writer.t("RECEIVINGS:").newLine();
        writer.h("COMPANY").h("TIME").h("RECEIVER").newLine();
        for (int i = 0; i < receivings.size(); i++){
            Recieving r = (Recieving)receivings.get(i);
            writer
                    .v(r.getCompany().getName())
                    .v(r.getTime())
                    .v(r.getReciever().getFullName());
        }
        writer.flush();
        return writer.getBytes();
    }

    public static byte[] generateAcceptors(DocumentWriter writer){
            Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
            dbSession.beginTransaction();
            Position position = (Position) dbSession.createCriteria(Position.class).
                    add(Restrictions.eq("name", "Acceptor")).uniqueResult();
            Criteria criteria = dbSession.createCriteria(Employee.class).add(Restrictions.eq("position", position));

            List acceptors = criteria.list();

            writer.t("ACCEPTORS:").newLine();
            writer.h("FULL NAME").h("LOGIN").h("PHONE NUMBER").h("TAKING OFFICE").h("SHIFTS COUNT").newLine();
            for (int i = 0; i < acceptors.size(); i++){
                Employee e = (Employee) acceptors.get(i);
                criteria = dbSession.createCriteria(AcceptorShift.class);
                Integer count = criteria.add(Restrictions.eq("acceptor",e)).list().size();

                if (!e.getFired()){
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

    public static byte[] generateAcceptorShifts(DocumentWriter writer){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(AcceptorShift.class);
        List shifts = criteria.list();
        writer.t("ACCEPTOR SHIFTS:").newLine();
        writer.h("ACCEPTOR NAME").h("BEGINNING").h("ENDING").h("VISITED CELL").h("IS PRODUCT TAKEN").h("PRODUCT NAME").newLine();
        for (int i = 0; i < shifts.size(); i++){
            AcceptorShift s = (AcceptorShift) shifts.get(i);
            criteria = dbSession.createCriteria(CellVisiting.class);
            List visitings = criteria.add(Restrictions.eq("shift",s)).list();
            writer.v(s.getAcceptor().getFullName());
            writer.v(s.getBegining());
            writer.v(s.getEnding());

            for (int j = 0; j < visitings.size(); j++) {
                CellVisiting v = (CellVisiting)visitings.get(j);
                writer.newLine().v("").v("").v("");
                writer
                    .v(v.getCell().getNumber())
                    .v(v.getProductTaken()?"Yes":"No")
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

    public static byte[] generateCells(DocumentWriter writer){
        Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        dbSession.beginTransaction();
        Criteria criteria = dbSession.createCriteria(Cell.class);
        List cells = criteria.list();
        writer.t("CELLS:").newLine();
        writer.h("CELL NUMBER").h("PRODUCT NAME").h("BARCODE").h("PUTTING DATE").h("ACCEPTOR").newLine();
        for (int i = 0; i < cells.size(); i++){
            Cell c = (Cell) cells.get(i);

            criteria = dbSession.createCriteria(CellVisiting.class);
            List visitings = criteria.add(Restrictions.eq("cell",c)).list();

            writer.v(c.getNumber());

            for (int j = 0; j < visitings.size(); j++) {
                CellVisiting v = (CellVisiting)visitings.get(j);
                if (!v.getProductTaken()){
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
