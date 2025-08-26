package github.AlissonMartin.emailService.core.dto;

import github.AlissonMartin.emailService.core.enums.Interval;

public record CreateTopicInput(String name, int interval, Interval intervalUnit) {
}
