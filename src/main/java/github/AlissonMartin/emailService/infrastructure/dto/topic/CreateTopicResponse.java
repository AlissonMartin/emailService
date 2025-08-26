package github.AlissonMartin.emailService.infrastructure.dto.topic;

import github.AlissonMartin.emailService.core.enums.Interval;

public record CreateTopicResponse(Long id, String name, int interval, Interval intervalUnit) {
}
