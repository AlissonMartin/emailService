package github.AlissonMartin.emailService.infrastructure.dto.topic;

import github.AlissonMartin.emailService.core.enums.Interval;

public record CreateTopicRequest(String name, int interval, Interval intervalUnit) {
}
