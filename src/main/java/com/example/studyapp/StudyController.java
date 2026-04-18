package com.example.studyapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;
import java.time.temporal.WeekFields;
import java.util.stream.Collectors;

@Controller
public class StudyController {

    private final StudyRepository repository;

    public StudyController(StudyRepository repository) {
        this.repository = repository;
    }

    // =========================
    // 画面表示
    // =========================
    @GetMapping("/")
    public String index(Model model) {

        List<Study> list = repository.findAll();

        int total = list.stream()
                .mapToInt(Study::getTime)
                .sum();

        model.addAttribute("list", list);
        model.addAttribute("total", total);

        return "index";
    }

    // =========================
    // 追加
    // =========================
    @PostMapping("/add")
    public String add(@RequestParam int time) {

        Study study = new Study(time, LocalDate.now());
        repository.save(study);

        return "redirect:/";
    }

    // =========================
    // 削除（画面用）
    // =========================
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {

        repository.deleteById(id);

        return "redirect:/";
    }


private boolean isStreakDay(LocalDate date, Set<LocalDate> allDates) {

    return allDates.contains(date.minusDays(1));
}
    // =========================
    // カレンダーイベント
    // =========================
    @GetMapping("/api/study-events")
    @ResponseBody
    public List<Map<String, Object>> events() {

        List<Study> studies = repository.findAll();

        Set<LocalDate> dates = studies.stream().map(Study::getStudyDate).collect(Collectors.toSet());

       return studies.stream().map(s -> {

            Map<String, Object> map = new HashMap<>();

            map.put("id", s.getId());
            map.put("title", s.getTime() + "分");
            map.put("start", s.getStudyDate());

                    int time = s.getTime();

            String color;

            if (time >= 180) {
                color = "#ff4500";
            } else if (time >= 120) {
                color = "#ff9800";
            } else if (time >= 60) {
                color = "#4caf50";
            } else if (time >= 30) {
                color = "#2196f3";
            } else {
                color = "#9e9e9e";
            }

            map.put("color", color);

            // ⭐ここ（カレンダー用の軽い判定）
            boolean streak = dates.contains(s.getStudyDate().minusDays(1));
            map.put("streak", streak);

            return map;
        }).toList();
    }
    // =========================
    // 日付追加
    // =========================
    @PostMapping("/add-by-date")
    @ResponseBody
    public Map<String, String> addByDate(@RequestParam int time,
                                         @RequestParam String date) {

        Study study = new Study();
        study.setTime(time);
        study.setStudyDate(LocalDate.parse(date));

        repository.save(study);

        return Map.of("status", "ok");
    }

    // =========================
    // 週合計
    // =========================
    @GetMapping("/api/weekly-summary")
    @ResponseBody
    public List<Map<String, Object>> weeklySummary() {

        WeekFields weekFields = WeekFields.of(Locale.JAPAN);

        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        s -> s.getStudyDate().getYear()
                                + "-W"
                                + s.getStudyDate().get(weekFields.weekOfWeekBasedYear()),
                        Collectors.summingInt(Study::getTime)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("week", e.getKey());
                    map.put("total", e.getValue());
                    return map;
                })
                .toList();
    }

    // =========================
    // ストリーク
    // =========================
    @GetMapping("/api/streak")
    @ResponseBody
    public Map<String, Object> streak() {

        List<LocalDate> dates = repository.findAll().stream()
                .map(Study::getStudyDate)
                .distinct()
                .sorted()
                .toList();

        int current = 0;
        int max = 0;
        int temp = 1;

        if (dates.isEmpty()) {
            return Map.of(
                    "current", 0,
                    "max", 0
            );
        }

        // max
        for (int i = 1; i < dates.size(); i++) {
            if (dates.get(i).minusDays(1).equals(dates.get(i - 1))) {
                temp++;
            } else {
                max = Math.max(max, temp);
                temp = 1;
            }
        }
        max = Math.max(max, temp);

        // current
        current = 1;
        for (int i = dates.size() - 1; i > 0; i--) {
            if (dates.get(i).minusDays(1).equals(dates.get(i - 1))) {
                current++;
            } else {
                break;
            }
        }

        return Map.of(
                "current", current,
                "max", max
        );
    }

    // =========================
    // ⭐更新（修正版）
    // =========================
    @PostMapping("/update-time")
    @ResponseBody
    public Map<String, String> updateTime(@RequestParam Long id,
                                          @RequestParam int time) {

        Study study = repository.findById(id)
                .orElseThrow();

        study.setTime(time);
        repository.save(study);

        return Map.of("status", "ok");
    }

    // =========================
    // ⭐削除（修正版）
    // =========================
    @PostMapping("/delete-by-id")
    @ResponseBody
    public Map<String, String> deleteById(@RequestParam Long id) {

        repository.deleteById(id);

        return Map.of("status", "ok");
    }

    @GetMapping("/api/monthly-summary")
    @ResponseBody
    public List<Map<String, Object>> monthlySummary() {

        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        s -> s.getStudyDate().getYear() + "-" + s.getStudyDate().getMonthValue(),
                        Collectors.summingInt(Study::getTime)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("month", e.getKey());
                    map.put("total", e.getValue());
                    return map;
                })
                .toList();
    }
}