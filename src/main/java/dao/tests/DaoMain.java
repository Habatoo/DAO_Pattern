package dao.tests;

import java.time.LocalDate;
import java.util.*;

abstract class Company {
    public String name;
    public int number;
    public LocalDate date;

    public Company(
            String name,
            int number,
            LocalDate date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{name=" + name +
                ", number=" + number +
                ", date=" + date + '}';
    }
}

class Customer extends Company {
    List<Supplier> suppliers;

    public Customer(
            String customerName,
            int customerNumber,
            LocalDate dateOfRegistration,
            List<Supplier> suppliers) {
        super(customerName, customerNumber, dateOfRegistration);
        this.suppliers = suppliers;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}

class Supplier extends Company {
    public Supplier(
            String supplierName,
            int supplierNumber,
            LocalDate dateOfRegistration) {
        super(supplierName, supplierNumber, dateOfRegistration);
    }
}

interface CustomerDao {
    void add(Customer customer);

    Customer findByCustomer(Customer customer);

    void update(Customer customer);

    void deleteByCustomer(Customer customer);

    List<Supplier> findAllSuppliers();
}

interface SupplierDao {
    void add(Supplier supplier);

    Supplier findBySupplier(Supplier supplier);

    void update(Supplier supplier);

    void deleteBySupplier(Supplier supplier);
}

class CustomerDaoImpl implements CustomerDao {
    private List<Customer> storage;

    public CustomerDaoImpl(List storage) {
        this.storage = storage;
    }

    @Override
    public void add(Customer customer) {
        storage.add(customer);
        System.out.println(customer + ", added");
    }

    @Override
    public Customer findByCustomer(Customer customer) {
        for (Company cus : storage) {
            if (cus.getNumber() == customer.getNumber()
                    && cus instanceof Customer) {
                System.out.println(customer + ", found");
                return new Customer(
                        customer.getName(),
                        customer.getNumber(),
                        customer.getDate(),
                        customer.getSuppliers());
            }
        }
        System.out.println(
                "Customer: Id " + customer.getNumber() + ", not found");
        return null;
    }

    @Override
    public void update(Customer customer) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i) instanceof Customer
                    && storage.get(i).getNumber() == customer.getNumber()) {
                storage.get(i).setName(customer.getName());
                storage.get(i).setDate(customer.getDate());
                storage.get(i).setSuppliers(customer.getSuppliers());
                System.out.println(customer + ", updated");
                return;
            }
        }
        System.out.println(
                "Customer: Id " + customer.getNumber() + ", not found");
    }

    @Override
    public void deleteByCustomer(Customer customer) {
        if (storage.remove(customer)) {
            System.out.println(customer + ", deleted");
            return;
        } else {
            System.out.println(
                    "Customer: Id " + customer.getNumber() + ", not found");
        }
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        for (Company supplier : storage) {
            if (supplier instanceof Supplier) {
                suppliers.add((Supplier) supplier);
            }
        }
        return suppliers;
    }
}

class SupplierDaoImpl implements SupplierDao {
    private List<Supplier> storage;

    public SupplierDaoImpl(List storage) {
        this.storage = storage;
    }

    @Override
    public void add(Supplier supplier) {
        System.out.println(supplier + ", added");
        storage.add(supplier);
    }

    @Override
    public Supplier findBySupplier(Supplier supplier) {
        for (Company cus : storage) {
            if (cus.getNumber() == supplier.getNumber()
                    && cus instanceof Supplier) {
                System.out.println(supplier + ", found");
                return new Supplier(
                        supplier.getName(),
                        supplier.getNumber(),
                        supplier.getDate());
            }
        }
        System.out.println(
                "Supplier: Id " + supplier.getNumber() + ", not found");
        return null;
    }

    @Override
    public void update(Supplier supplier) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i) instanceof Supplier
                    && storage.get(i).getNumber() == supplier.getNumber()) {
                System.out.println(supplier + ", updated");
                storage.get(i).setName(supplier.getName());
                storage.get(i).setDate(supplier.getDate());
                return;
            }
        }
        System.out.println(
                "Supplier: Id " + supplier.getNumber() + ", not found");
    }

    @Override
    public void deleteBySupplier(Supplier supplier) {
        if (storage.remove(supplier)) {
            System.out.println(supplier + ", deleted");
            return;
        } else {
            System.out.println(
                    "Supplier: Id " + supplier.getNumber() + ", not found");
        }
    }
}

public class DaoMain {
    public static void main(String[] args) {
        List<Company> storage = new ArrayList<>();
        CustomerDaoImpl customerDao = new CustomerDaoImpl(storage);
        SupplierDaoImpl supplierDao = new SupplierDaoImpl(storage);

        Supplier supplier1 = new Supplier(
                "FirstSupplier",
                1,
                LocalDate.of(2022, 4, 12));
        Supplier supplier2 = new Supplier(
                "SecondSupplier",
                2,
                LocalDate.of(2022, 4, 13));
        Supplier supplier3 = new Supplier(
                "ThirdSupplier",
                3,
                LocalDate.of(2022, 4, 14));

        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        supplierDao.add(supplier3);

        List<Supplier> firstList = new ArrayList<>();
        firstList.add(supplier1);
        firstList.add(supplier2);

        List<Supplier> secondList = new ArrayList<>();
        secondList.add(supplier3);

        Customer customer1 = new Customer(
                "FirstCustomer",
                1,
                LocalDate.of(2022, 4, 9), firstList);
        Customer customer2 = new Customer(
                "SecondCustomer",
                2,
                LocalDate.of(2022, 4, 10), secondList);

        customerDao.add(customer1);
        customerDao.add(customer2);

        supplierDao.findBySupplier(supplier1);
        customerDao.findByCustomer(customer2);

        System.out.println(customerDao.findAllSuppliers());

        for (Company company : storage) {
            System.out.println(company);
        }

        supplierDao.update(new Supplier(
                "ThirdSupplier+",
                3,
                LocalDate.now().minusDays(2)));
        customerDao.update(new Customer(
                "FirstCustomer+",
                1,
                LocalDate.now(), firstList));

        for (Company company : storage) {
            System.out.println(company);
        }
        supplierDao.deleteBySupplier(supplier1);
        customerDao.deleteByCustomer(customer2);

        for (Company company : storage) {
            System.out.println(company);
        }
    }
}