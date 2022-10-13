package org.cnolan;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.cnolan.output.DroidPositionWriter;
import org.cnolan.output.OutputWriter;
import org.cnolan.parser.DroidParser;
import org.cnolan.parser.Parser;
import org.cnolan.parser.ParserReader;
import org.cnolan.parser.PlainDroidFormatStringParser;
import org.cnolan.parser.RectangleMapParser;
import org.cnolan.simulation.DroidSimulation;
import org.cnolan.simulation.Simulation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ParserReader parserReader = new ParserReader();
            RectangleMapParser mapParser = new RectangleMapParser();
            DroidParser droidParser = new DroidParser();
            Parser<String> parser = new PlainDroidFormatStringParser(droidParser, mapParser, parserReader);
            Simulation simulation = new DroidSimulation();
            OutputWriter outputWriter = new DroidPositionWriter();
            SimulationPipeline<String> sim = new SimulationPipeline<>(parser, simulation, outputWriter);

            try (Scanner scanner = new Scanner(System.in)){
                StringBuffer input = new StringBuffer();
                
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    input.append(line);
                    input.append("\n");
                }

                sim.run(input.toString());
            }
        };
    }
}