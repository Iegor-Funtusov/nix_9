package ua.com.alevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public void test() {
//        List list = Arrays.asList(1, 5, 3, 9, 8, 4, 1, 78);
//
//        Stream<Integer> integerStream = list.stream();
//
//        integerStream = Stream.of(1, 5, 3, 9, 8, 4, 1, 78);
//
//        List<Integer> res = integerStream.
//                distinct().
//                filter((Integer i) -> i % 2 == 0).
//                sorted().
//                collect(Collectors.toList());
//
//
//
//        res = integerStream.
//                distinct().
//                filter(this::isEven).
//                sorted().
//                collect(Collectors.toList());
//
//
//
//        res = integerStream.
//                distinct().
//                filter(EvenUtil::isEven).
//                sorted().
//                collect(Collectors.toList());

        List<Customer> customers = new ArrayList<>();
        List<CustomerDto> customersDtos = new ArrayList<>();
        List<String> fullNames;

        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setId((long) i);
            customer.setCreated(new Date());
            if (i == 5) {
                customer.setFirstName("ame1" + i);
            } else {
                customer.setFirstName("name1" + i);
            }
            customer.setLastName("name2" + i);
            customers.add(customer);
        }

        long count = customers
                .stream()
                .filter(customer -> customer.getFirstName().startsWith("ame"))
                .count();

        System.out.println("count = " + count);

        //1
//        for (Customer customer : customers) {
//            CustomerDto customerDto = new CustomerDto();
//            customerDto.setId(customer.getId());
//            customerDto.setCreated(customer.getCreated().getTime());
//            customerDto.setFullName(customer.getFirstName() + " " + customer.getLastName());
//            customersDtos.add(customerDto);
//        }

        //2
//        customersDtos = customers
//                .stream()
//                .map(customer -> {
//                    CustomerDto customerDto = new CustomerDto();
//                    customerDto.setId(customer.getId());
//                    customerDto.setCreated(customer.getCreated().getTime());
//                    customerDto.setFullName(customer.getFirstName() + " " + customer.getLastName());
//                    return customerDto;
//                }).collect(Collectors.toList());

        //3
//        customersDtos = customers
//                .stream()
//                .map(customer -> new CustomerDto(customer))
//                .collect(Collectors.toList());

        //4
        customersDtos = customers
                .stream()
                .map(CustomerDto::new)
                .collect(Collectors.toList());

        for (CustomerDto customersDto : customersDtos) {
            System.out.println("customersDto = " + customersDto);
        }

        fullNames = customersDtos
                .stream()
                .map(CustomerDto::getFullName)
                .collect(Collectors.toList());

        fullNames.forEach(System.out::println);

        boolean endWith26 = customersDtos
                .stream()
                .anyMatch(customerDto -> customerDto.getFullName().endsWith("26"));
        System.out.println("endWith26 = " + endWith26);

        boolean startWithName = customersDtos
                .stream()
                .allMatch(customerDto -> customerDto.getFullName().startsWith("name"));
        System.out.println("startWithName = " + startWithName);
    }

    public boolean isEven(Integer integer) {
        return integer % 2 == 0;
    }
}
