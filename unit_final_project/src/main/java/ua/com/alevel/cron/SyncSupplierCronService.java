package ua.com.alevel.cron;

import org.springframework.stereotype.Service;
import ua.com.alevel.service.SupplierService;

@Service
public class SyncSupplierCronService {

    private final SupplierService supplierService;

    public SyncSupplierCronService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //    @Scheduled(cron = "* * * ? * *")
//    @Scheduled(fixedDelay = 5000)
    public void syncToSupplier() {
        System.out.println("SyncSupplierCronService.syncToSupplier");
        supplierService.syncToSupplier();
    }
}
