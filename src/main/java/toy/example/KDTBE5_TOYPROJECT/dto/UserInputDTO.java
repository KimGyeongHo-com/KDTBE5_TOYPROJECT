package toy.example.KDTBE5_TOYPROJECT.dto;

import java.util.HashMap;
import java.util.Map;

public class UserInputDTO {
    private String menu;
    private Map<String, String> arguments;

    public UserInputDTO(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        // 만약 arguments가 필요한 기능이라면 arguments값이 service에서 null인지 판단해줘야 한다.
        String[] parts = userInput.split("\\?");
        this.menu = parts[0];
        this.arguments = parts.length > 1 ? parseArguments(parts[1]) : null;
    }

    private Map<String, String> parseArguments(String parameterString) {
        Map<String, String> arguments = new HashMap<>();
        String[] keyValuePairs = parameterString.split("&");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                arguments.put(keyValue[0], keyValue[1]);
            }
        }
        return arguments;
    }

    public String getMenu() {
        return menu;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public String getArgument(String key) {
        return arguments.get(key);
    }
}
