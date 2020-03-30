package eggplant.backend.controller;

import eggplant.backend.model.EggplantConfig;
import eggplant.backend.service.EggplantConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EggplantConfigController {

    static final String basePath = "config";

    @Autowired
    private EggplantConfigService configService;

    @GetMapping(value = basePath)
    public EggplantConfig getConfig() {
        return configService.getConfig();
    }

}
