package ua.com.alevel.service.impl;

import nix.solutions.dinix.annotations.Autowired;
import nix.solutions.dinix.annotations.Service;
import ua.com.alevel.dao.BlaDao;
import ua.com.alevel.entity.Bla;
import ua.com.alevel.service.BlaService;

import java.util.Collection;

@Service
public class BlaServiceImpl implements BlaService {

    @Autowired
    private BlaDao blaDao;

    @Override
    public void create(Bla entity) {

    }

    @Override
    public void update(Bla entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Bla findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Bla> findAll() {
        return null;
    }
}
