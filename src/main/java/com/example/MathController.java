package com.example;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/math")
public class MathController {
    @GetMapping("/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String doCalculation(OperationInfo operationInfo) {
        return String.format("%s", operationInfo.buildString());
    }

    @PostMapping("/sum")
    public String getMapParams(@RequestParam MultiValueMap querystring) {
        Integer sum = 0;
        Object numbers = querystring.get("n");
        String[] numbersString = numbers.toString().replace("[", "").replace("]", "").split(", ");

        for (String number : numbersString) {
            sum += Integer.parseInt(number);
        }
        return (sum.toString());
    }

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable String length, @PathVariable String width, @PathVariable String height) {
        Integer volume = (Integer.parseInt(length) * Integer.parseInt(width) * Integer.parseInt(height));
        return String.format("The volume of a %sx%sx%s rectangle is %s", length, width, height, volume.toString());
    }

}
