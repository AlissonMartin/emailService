package github.AlissonMartin.emailService.core.entities;

import github.AlissonMartin.emailService.core.enums.Interval;

public record Topic(Long id, String name, int intervalValue, Interval intervalUnit) {
}
