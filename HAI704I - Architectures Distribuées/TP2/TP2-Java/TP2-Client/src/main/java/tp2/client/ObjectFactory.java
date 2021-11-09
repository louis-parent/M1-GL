
package tp2.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tp2.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FetchAvailableOffers_QNAME = new QName("http://service.server.tp2/", "fetchAvailableOffers");
    private final static QName _FetchAvailableOffersResponse_QNAME = new QName("http://service.server.tp2/", "fetchAvailableOffersResponse");
    private final static QName _HelloWorld_QNAME = new QName("http://service.server.tp2/", "helloWorld");
    private final static QName _HelloWorldResponse_QNAME = new QName("http://service.server.tp2/", "helloWorldResponse");
    private final static QName _MakeReservation_QNAME = new QName("http://service.server.tp2/", "makeReservation");
    private final static QName _MakeReservationResponse_QNAME = new QName("http://service.server.tp2/", "makeReservationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tp2.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FetchAvailableOffers }
     * 
     */
    public FetchAvailableOffers createFetchAvailableOffers() {
        return new FetchAvailableOffers();
    }

    /**
     * Create an instance of {@link FetchAvailableOffersResponse }
     * 
     */
    public FetchAvailableOffersResponse createFetchAvailableOffersResponse() {
        return new FetchAvailableOffersResponse();
    }

    /**
     * Create an instance of {@link HelloWorld }
     * 
     */
    public HelloWorld createHelloWorld() {
        return new HelloWorld();
    }

    /**
     * Create an instance of {@link HelloWorldResponse }
     * 
     */
    public HelloWorldResponse createHelloWorldResponse() {
        return new HelloWorldResponse();
    }

    /**
     * Create an instance of {@link MakeReservation }
     * 
     */
    public MakeReservation createMakeReservation() {
        return new MakeReservation();
    }

    /**
     * Create an instance of {@link MakeReservationResponse }
     * 
     */
    public MakeReservationResponse createMakeReservationResponse() {
        return new MakeReservationResponse();
    }

    /**
     * Create an instance of {@link Offer }
     * 
     */
    public Offer createOffer() {
        return new Offer();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchAvailableOffers }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FetchAvailableOffers }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.server.tp2/", name = "fetchAvailableOffers")
    public JAXBElement<FetchAvailableOffers> createFetchAvailableOffers(FetchAvailableOffers value) {
        return new JAXBElement<FetchAvailableOffers>(_FetchAvailableOffers_QNAME, FetchAvailableOffers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchAvailableOffersResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FetchAvailableOffersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.server.tp2/", name = "fetchAvailableOffersResponse")
    public JAXBElement<FetchAvailableOffersResponse> createFetchAvailableOffersResponse(FetchAvailableOffersResponse value) {
        return new JAXBElement<FetchAvailableOffersResponse>(_FetchAvailableOffersResponse_QNAME, FetchAvailableOffersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorld }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloWorld }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.server.tp2/", name = "helloWorld")
    public JAXBElement<HelloWorld> createHelloWorld(HelloWorld value) {
        return new JAXBElement<HelloWorld>(_HelloWorld_QNAME, HelloWorld.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloWorldResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.server.tp2/", name = "helloWorldResponse")
    public JAXBElement<HelloWorldResponse> createHelloWorldResponse(HelloWorldResponse value) {
        return new JAXBElement<HelloWorldResponse>(_HelloWorldResponse_QNAME, HelloWorldResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeReservation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeReservation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.server.tp2/", name = "makeReservation")
    public JAXBElement<MakeReservation> createMakeReservation(MakeReservation value) {
        return new JAXBElement<MakeReservation>(_MakeReservation_QNAME, MakeReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeReservationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeReservationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.server.tp2/", name = "makeReservationResponse")
    public JAXBElement<MakeReservationResponse> createMakeReservationResponse(MakeReservationResponse value) {
        return new JAXBElement<MakeReservationResponse>(_MakeReservationResponse_QNAME, MakeReservationResponse.class, null, value);
    }

}
