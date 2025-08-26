package github.AlissonMartin.emailService.core.dto;

import github.AlissonMartin.emailService.core.enums.Interval;

public record CreateTopicOutput(Long id, String name, int interval, Interval intervalUnit) {
}
