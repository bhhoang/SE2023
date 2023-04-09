package a1_BI12_170;

/**
 * @overview An under graduate student being that is characterized by an id, name, phoneNumber and address.
 * @attributes
 * <pre>
 * <b>list of attributes:</b>
 *     id              Integer     int
 *     name            String
 *     phoneNumber     String
 *     address         String
 * </pre>
 * 
 * @object A typical UndergradStudent is <i,s,p,a>, where <i,s,p,a> is the id, name, phoneNumber and address of the student.
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1e5 /\ max(id)=1e8 /\
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * 
 * @author bhhoang
 */
public class UndergradStudent {
    //Override constants
    public static final Integer MIN_ID = (int) 1e5;
    public static final Integer MAX_ID = (int) 1e8;
}
