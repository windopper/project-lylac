package net.kamilereon.lylac.general;

import org.bson.Document;

/**
 * Document 오브젝트로 부터 해당 객체가 업데이트 될 수 있다는 것을 대표함
 */
public interface Loadable {
    public void load(Document doc);
}
