package ro.msg.learning.shop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.msg.learning.shop.service.StockService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    private final static Logger logger = Logger.getLogger(StockController.class);

    @RequestMapping("/{locationId}")
    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public void exportStocks(@PathVariable(value = "locationId") int locationId, HttpServletResponse response) {
        try {
            stockService.exportStocksToCsv(locationId);
            Path file = Paths.get(StockService.FILE_NAME);
            Files.copy(file, response.getOutputStream());
            response.setContentType("text/csv");
            response.setHeader("Content-disposition", "attachment; filename=" + "stocks.csv");
            response.flushBuffer();
        } catch (IOException ex) {
            logger.debug("Could not export stocks to CSV", ex);
        }
    }
}
