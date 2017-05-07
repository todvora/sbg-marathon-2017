package cz.tomasdvorak.sbg.marathon.y2017;

import cz.tomasdvorak.sbg.marathon.y2017.dto.Gender;
import cz.tomasdvorak.sbg.marathon.y2017.dto.Participant;
import cz.tomasdvorak.sbg.marathon.y2017.dto.Result;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws IOException {
        final Parser parser = new Parser(Application.class.getResourceAsStream("/marathon.json"));

        System.out.println("--------------------------");
        System.out.println("Runners by countries");
        parser.getResults().stream().collect(Collectors.groupingBy(a -> a.getFirstParticipant().getIaaf(), Collectors.counting())).entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).forEach(e -> {
            System.out.println(e.getKey() + " " + e.getValue());
        });
        System.out.println("Countries count: " + parser.getResults().stream().map(s->s.getFirstParticipant().getIaaf()).distinct().count());
        System.out.println("--------------------------");

        System.out.println("Runners status");
        final int total = parser.getResults().size();
        System.out.println("Total " + total);
        parser.getResults().stream().collect(Collectors.groupingBy(Result::getState, Collectors.counting())).entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).forEach(e -> {
            System.out.println(e.getKey() + " " + e.getValue() + "("+100.0 / total * e.getValue()+"%)");
        });
        System.out.println("--------------------------");

        System.out.println("Finish time histogram");
        final Map<String, Long> times = parser.getResults().stream()
                .filter(s -> s.getState().equals("reg"))
                .map(Result::getTime)
                .collect(Collectors.groupingBy(Application::toBucket, Collectors.counting()));
        System.out.println("Times: " + times);


        System.out.println("--------------------------");
        System.out.println("Age decade histogram");
        final Map<String, Long> age = parser.getResults().stream().map(s -> toAge(s.getFirstParticipant().getBirthyear())).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Age " + age);
        final Result youngest = parser.getResults().stream().sorted(Comparator.comparing(s -> s.getFirstParticipant().getBirthyear(), Comparator.reverseOrder())).findFirst().get();
        System.out.println(youngest);
        System.out.println("--------------------------");

        final Map<Gender, Long> byGender = parser.getResults().stream().map(s -> s.getFirstParticipant().getGender()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Gender " + byGender);

        System.out.println("--------------------------");
        System.out.println("Fastest runners ");
        final Result male = parser.getResults().stream()
                .filter(s -> s.getFirstParticipant().getGender() == Gender.Male)
                .filter(s->s.getState().equals("reg"))
                .sorted(Comparator.comparing(s -> toTime(s.getTime()))).findFirst().get();

        final Result female = parser.getResults().stream()
                .filter(s -> s.getFirstParticipant().getGender() == Gender.Female)
                .filter(s->s.getState().equals("reg"))
                .sorted(Comparator.comparing(s -> toTime(s.getTime()))).findFirst().get();
        System.out.println(toFastestTimeInfo(male));
        System.out.println(toFastestTimeInfo(female));
        System.out.println("--------------------------");
    }

    private static String toFastestTimeInfo(final Result res) {
        final Participant person = res.getFirstParticipant();
        final Gender gender = person.getGender();
        final String name = person.getFirstname() + " " + person.getLastname();
        final Calendar c = Calendar.getInstance();
        final int currentYear = c.get(Calendar.YEAR);
        final int age = currentYear - person.getBirthyear();
        final LocalTime toTime = toTime(res.getTime());
        final String country = person.getIaaf();
        return String.format("Gender: %s, name: %s, country: %s, age: %d, time: %s", gender, name, country, age, toTime);
    }

    private static LocalTime toTime(final String time) {
        try {
            DateFormat format = new SimpleDateFormat("hh:mm:ss");
            final Date parsed = format.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(parsed);
            return LocalTime.of(c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toAge(final int birthYear) {
        final Calendar c = Calendar.getInstance();
        final int currentYear = c.get(Calendar.YEAR);
        final int age = currentYear - birthYear;
        final int decade = (age / 10) * 10;
        return decade + "-" + (decade + 9);
    }

    private static String toBucket(final String time) {
        DateFormat sdfParse = new SimpleDateFormat("hh:mm:ss");
        try {
            final Date parsed = sdfParse.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(parsed);
            final int minutes = c.get(Calendar.MINUTE);
            final int i = minutes / 30;
            final int hour = c.get(Calendar.HOUR);
            return hour + ":" + i * 30 + "-" + hour + ":" + ((i + 1) * 30 - 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
