package com.example;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;


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

    @PostMapping(path = "/area")
    public String getArea(@RequestParam Map<String, String> body) {
        DecimalFormat numberFormat = new DecimalFormat("#.00000");
        try {
            if (body.get("shape").equals("circle")) {
                Integer radius = Integer.parseInt(body.get("radius"));
                System.out.println(radius);
                Double area = 3.141592653589793 * radius * radius;
                return String.format("Area of a circle with a radius of %s is %s", radius.toString(), numberFormat.format(area));
            } else if (body.get("shape").equals("rectangle")) {
                Integer width = Integer.parseInt(body.get("width"));
                Integer height = Integer.parseInt(body.get("height"));
                Integer area = height * width;
                return String.format("Area of a %sx%s rectangle is %s", width.toString(), height.toString(), area.toString());
            } else {
                return "Invalid";
            }
        } catch (Exception e) {
            return "Invalid";
        }
    }

}
