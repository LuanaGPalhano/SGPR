package org.example.DTO;

import java.util.*;

public record ResumoNutricionalRequest(
    List<Map<String, String>> porcoes
)
{}
