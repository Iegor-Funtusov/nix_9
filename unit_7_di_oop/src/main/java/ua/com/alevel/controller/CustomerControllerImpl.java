package ua.com.alevel.controller;

import nix.solutions.dinix.annotations.Autowired;
import nix.solutions.dinix.annotations.Service;
import ua.com.alevel.dto.CustomerRequestDto;
import ua.com.alevel.dto.CustomerResponseDto;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.facade.CustomerFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerFacade customerFacade;

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create user, please enter 1");
        System.out.println("if you want update user, please enter 2");
        System.out.println("if you want delete user, please enter 3");
        System.out.println("if you want findById user, please enter 4");
        System.out.println("if you want findAll user, please enter 5");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" : create(reader); break;
            case "2" : update(reader); break;
            case "3" : delete(reader); break;
            case "4" : findById(reader); break;
            case "5" : findAll(reader); break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("UserController.create");
        try {
            System.out.println("Please, enter your first name");
            String firstName = reader.readLine();
            System.out.println("Please, enter your last name");
            String lastName = reader.readLine();
            System.out.println("Please, enter your email");
            String email = reader.readLine();

            CustomerRequestDto dto = new CustomerRequestDto();
            dto.setFirstName(firstName);
            dto.setLastName(lastName);
            dto.setEmail(email);

            customerFacade.create(dto);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("UserController.update");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter your name");
            String name = reader.readLine();
            System.out.println("Please, enter your age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            CustomerRequestDto dto = new CustomerRequestDto();
            customerFacade.update(dto, Integer.parseInt(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("UserController.delete");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Integer idd = Integer.parseInt(id);
            customerFacade.delete(idd);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("UserController.findById");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Integer idd = Integer.parseInt(id);
            CustomerResponseDto user = customerFacade.findById(idd);
            System.out.println("user = " + user);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("UserController.findAll");
        Collection<CustomerResponseDto> users = customerFacade.findAll();
        if (users != null && users.size() != 0) {
            for (CustomerResponseDto dto : users) {
                System.out.println("user = " + dto);
            }
        } else {
            System.out.println("users empty");
        }
    }
}
