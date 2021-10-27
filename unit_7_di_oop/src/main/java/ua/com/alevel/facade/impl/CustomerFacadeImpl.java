package ua.com.alevel.facade.impl;

import nix.solutions.dinix.annotations.Autowired;
import nix.solutions.dinix.annotations.Service;

import ua.com.alevel.dto.CustomerRequestDto;
import ua.com.alevel.dto.CustomerResponseDto;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.facade.CustomerFacade;
import ua.com.alevel.service.CustomerService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerFacadeImpl implements CustomerFacade {

    @Autowired
    private CustomerService customerService;

    public CustomerFacadeImpl() {
        System.out.println("CustomerFacadeImpl.CustomerFacadeImpl");
    }

    @Override
    public void create(CustomerRequestDto requestDto) {
        System.out.println("CustomerFacadeImpl.create");
        Customer customer = new Customer();
        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());
        customerService.create(customer);
    }

    @Override
    public void update(CustomerRequestDto requestDto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public CustomerResponseDto findById(Integer id) {
        return null;
    }

    @Override
    public Collection<CustomerResponseDto> findAll() {
        return customerService.findAll().stream().map(CustomerResponseDto::new).collect(Collectors.toList());
    }
}
