/**
 * Copyright 2012-2013 the Semargl contributors. See AUTHORS for more details.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.semarglproject.rdf.rdfa;

import java.util.HashMap;
import java.util.Map;

final class VocabManager {

    private final Map<String, Vocabulary> vocabCache = new HashMap<String, Vocabulary>();

    Vocabulary findVocab(String vocabUrl, boolean expandVocab) {
        Vocabulary vocab = vocabCache.get(vocabUrl);
        if (vocab != null) {
            return vocab;
        }
        vocab = new Vocabulary(vocabUrl);
        vocabCache.put(vocabUrl, vocab);
        if (expandVocab) {
            vocab.load();
        }
        return vocab;
    }
}
