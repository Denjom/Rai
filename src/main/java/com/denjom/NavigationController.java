package com.denjom;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavigationController {

    private static final Navigation nav = JsonMapper.loadAndParseNavigationJson();

    @RequestMapping(method=RequestMethod.GET, value={"/{id}"})
    public String greeting(@PathVariable("id") String id) {
        return JsonMapper.getNodeById(id != null ? id : JsonMapper.ROOT_NODE, nav);
    }
}